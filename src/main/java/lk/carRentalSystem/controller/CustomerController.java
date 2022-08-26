package lk.carRentalSystem.controller;


import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.service.CustomerService;
import lk.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil saveCustomer(@RequestPart("files") MultipartFile[] files, @RequestPart("customer") CustomerDTO dto) {

        for (MultipartFile file : files) {
            try {
                String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                file.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + file.getOriginalFilename()));

            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }

        service.saveCustomer(dto);

        return new ResponseUtil(200, "Save", null);
    }

    @PutMapping(/*consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}*/ produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updateCustomer(@RequestPart("files") MultipartFile[] files, @RequestPart("customer") CustomerDTO dto){
        for (MultipartFile file : files) {
            String projectPath = null;
            try {
                projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
                File uploadsDir = new File(projectPath + "/uploads");
                uploadsDir.mkdir();
                file.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + file.getOriginalFilename()));

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        service.updateCustomer(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @GetMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCustomerDetails(@RequestParam String id){
        CustomerDTO customerDTO = service.searchCustomer(id);
        return new ResponseUtil(200, "done", customerDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCustomer(@RequestParam String id){
        service.deleteCustomer(id);
        return new ResponseUtil(200, "done", null);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseUtil getAllCustomerDetails(){
        return new ResponseUtil(200, "Updated", service.getAllCustomers());
    }


}
