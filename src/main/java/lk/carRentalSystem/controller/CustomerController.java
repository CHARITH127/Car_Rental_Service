package lk.carRentalSystem.controller;


import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.service.CustomerService;
import lk.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil test(@RequestPart("files") MultipartFile[] files, @RequestPart("customer") CustomerDTO dto) {

        for (MultipartFile file : files) {
            String projectPath = new File("/home/charith/Documents/Testing/CarRentalSystem/src/main/webapp/").getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            uploadsDir.mkdir();
            try {
                file.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + file.getOriginalFilename()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        service.saveCustomer(dto);

        return new ResponseUtil(200, "Save", null);
    }

    public ResponseUtil updateCustomer(@RequestPart("files") MultipartFile[] files, @RequestPart("customer") CustomerDTO dto){

        service.updateCustomer(dto);
        return new ResponseUtil(200, "Save", null);
    }
}
