package lk.carRentalSystem.controller;

import lk.carRentalSystem.dto.AdminDTO;
import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.service.AdminLoginService;
import lk.carRentalSystem.service.CustomerService;
import lk.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController {

    @Autowired
    AdminLoginService adminLoginService;

    @Autowired
    CustomerService customerService;

    @GetMapping(params = {"adminUserName","adminPassword"})
    public ResponseUtil verifiedAdminLogin(String adminUserName , String adminPassword){
        AdminDTO adminDTO = adminLoginService.getAdminObject(adminUserName, adminPassword);
        if (adminDTO==null) {
            return new ResponseUtil(200,"Check the User Name And Password",null);
        }else {
            return new ResponseUtil(200,"Login Accepted",null);
        }
    }

    @GetMapping(params = {"customerUserName","customerPassword"})
    public ResponseUtil verifiedCustomerLogin(String customerUserName , String customerPassword){
        CustomerDTO customerDTO = customerService.getCustomerByUserNamePassword(customerUserName, customerPassword);
        if (customerDTO==null) {
            return new ResponseUtil(200,"Check the User Name And Password",null);
        }else {
            return new ResponseUtil(200,"Login Accepted",null);
        }
    }

}
