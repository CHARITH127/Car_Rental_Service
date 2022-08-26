package lk.carRentalSystem.controller;

import lk.carRentalSystem.dto.AdminDTO;
import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.dto.DriverDTO;
import lk.carRentalSystem.service.AdminLoginService;
import lk.carRentalSystem.service.CustomerService;
import lk.carRentalSystem.service.DriverService;
import lk.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController {

    @Autowired
    DriverService driverService;

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

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil verifiedLogin(@RequestParam String userId ,@RequestParam String password){
        System.out.println(userId+" "+password);
        CustomerDTO customerDTO = customerService.getCustomerByUserNamePassword(userId, password);
        DriverDTO driverDTO = driverService.getDriverLogin(userId, password);
        AdminDTO adminDTO = adminLoginService.getAdminObject(userId, password);

        if (!(customerDTO==null)) {
            return new ResponseUtil(200,"Customer",customerDTO);
        }else if (!(driverDTO==null)){
            return new ResponseUtil(200,"Driver",driverDTO);
        }else if (!(adminDTO==null)){
            return new ResponseUtil(200,"Admin",adminDTO);
        }else {
            return new ResponseUtil(200,"Login Id or password incorrect",null);
        }
    }



}
