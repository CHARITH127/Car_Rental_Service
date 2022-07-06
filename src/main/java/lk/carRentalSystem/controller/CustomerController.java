package lk.carRentalSystem.controller;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.entity.Customer;
import lk.carRentalSystem.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerServiceImpl service;

    @PostMapping()
    public String test(@RequestParam("idphoto") MultipartFile idphoto, @RequestParam("dlicen") MultipartFile dlicen, @RequestBody CustomerDTO customerDTO, ModelMap modelMap){
        Customer cs = new Customer();

        modelMap.addAttribute("idphoto",idphoto);
        modelMap.addAttribute("dlicen",dlicen);


        try {
            byte[] bytes1= idphoto.getBytes();

            System.out.println(idphoto.getOriginalFilename());
            System.out.println(idphoto.getName());
            System.out.println(idphoto.getContentType());
            System.out.println(idphoto.getSize());
            System.out.println(bytes1);

            System.out.println("===============================================");
            byte[] bytes2= dlicen.getBytes();

            System.out.println(dlicen.getOriginalFilename());
            System.out.println(dlicen.getName());
            System.out.println(dlicen.getContentType());
            System.out.println(dlicen.getSize());
            System.out.println(bytes2);

            System.out.println("===============================================");

            System.out.println(customerDTO.getName());
            System.out.println(customerDTO.getAddress());


        } catch (IOException e) {
            e.printStackTrace();
        }



        return "image upload successfully";
    }
}
