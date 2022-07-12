package lk.carRentalSystem.controller;

import lk.carRentalSystem.dto.BillingDTO;
import lk.carRentalSystem.dto.ReservationDTO;
import lk.carRentalSystem.service.BillingService;
import lk.carRentalSystem.service.ReservationService;
import lk.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("bill")
@CrossOrigin
public class BillingController {

    @Autowired
    BillingService billingService;

    @Autowired
    ReservationService reservationService;

    @GetMapping (params = {"bilId"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateBillId(){
        String billingId = billingService.generateBillingId();
        return new ResponseUtil(200,"done",billingId);
    }

    @PostMapping( produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil saveAPayment(@RequestPart("bill") BillingDTO billingDTO){
        ReservationDTO reservation = reservationService.getReservationById(billingDTO.getReservation().getReservation_id());
        billingDTO.setReservation(reservation);
        billingService.saveThePayment(billingDTO);
        return new ResponseUtil(200,"Payment successfully",null);
    }

    @GetMapping (params = {"weeklyIncome"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getWeeklyIncome(){
        Double income = billingService.getWeeklyIncome();
        return new ResponseUtil(200,"done",income);
    }

    @GetMapping (params = {"monthlyIncome"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getMonthlyIncome(){
        Double income = billingService.getMonthlyIncome();
        return new ResponseUtil(200,"done",income);
    }

    @GetMapping (params = {"yearlyIncome"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getYearlyIncome(){
        Double income = billingService.getYearlyIncome();
        return new ResponseUtil(200,"done",income);
    }

    @GetMapping (params = {"specific"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getYearlyIncome(@RequestParam Date date){
        Double income = billingService.getPaymentsOnSpecific(date);
        return new ResponseUtil(200,"done",income);
    }


}
