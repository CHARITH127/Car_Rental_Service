package lk.carRentalSystem.controller;

import lk.carRentalSystem.dto.*;
import lk.carRentalSystem.service.*;
import lk.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("reservation")
@CrossOrigin
public class ReservationController {
    @Autowired
    private ReservationService resService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverScheduleService scheduleService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil saveReservation(@RequestPart("files") MultipartFile files, @RequestPart("reservationWithOutDriver") ReservationDTO reservationDTO) {
        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            uploadsDir.mkdir();
            files.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + files.getOriginalFilename()));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerDTO customerDTO = customerService.searchCustomer(reservationDTO.getCustomer().getCustomerNic());
        CarDTO car = carService.searchCar(reservationDTO.getCar().getNumber());
        reservationDTO.setCar(car);
        reservationDTO.setCustomer(customerDTO);


        if (reservationDTO.getDriverStatus().equals("No")) {
            System.out.println(reservationDTO);
            resService.saveReservationWithoutDriver(reservationDTO);
        } else {
            Date pick_up_date = reservationDTO.getPick_up_date();
            Date return_date = reservationDTO.getReturn_date();
            Time pick_up_time = reservationDTO.getPick_up_time();
            DriverDTO randomDriver = driverService.getRandomDriver(reservationDTO.getPick_up_date(), reservationDTO.getReturn_date());

            /*check the driver is available or not*/
            if (randomDriver == null) {
                return new ResponseUtil(200, "Sorry our all drivers booked according to this date range", null);
            } else {
                DriverScheduleDTO scheduleDTO = new DriverScheduleDTO();
                scheduleDTO.setPick_up_date(pick_up_date);
                scheduleDTO.setReturn_date(return_date);
                scheduleDTO.setPick_up_time(pick_up_time);
                scheduleDTO.setDriver(randomDriver);
                scheduleDTO.setReservation(reservationDTO);

                System.out.println(scheduleDTO);
                scheduleService.saveDriverSchedule(scheduleDTO);
            }
        }
        return new ResponseUtil(200, "Reservation Send..", null);
    }

    @GetMapping(params = {"resID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateReservationId(){
        String resID = resService.generateReservationId();
        return new ResponseUtil(200,resID,null);
    }

    @GetMapping(params = {"cId"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getReservationByCustomer(@RequestParam String cId){
       List<ReservationDTO> resID = resService.getReservationByCustomer(cId);
       return new ResponseUtil(200,"done",resID);
    }

    @DeleteMapping(params = {"reservationId"})
    public ResponseUtil deleteReservation(@RequestParam String reservationId){
        resService.deleteReservation(reservationId);
        return new ResponseUtil(200,"Reservation Deleted",null);
    }

    @GetMapping(params = {"pending"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getReservationByCustomerAboutToAccept(){
        List<ReservationDTO> resID = resService.getReservationAboutToAccept();
        return new ResponseUtil(200,"done",resID);
    }

    @GetMapping(params = {"todayDate"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getTodayResrvations(@RequestParam Date todayDate){
        return new ResponseUtil(200,"done",resService.getTodayReserving(todayDate));
    }

    @GetMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getReservationById(@RequestParam String id){
        return new ResponseUtil(200,"done",resService.getReservationById(id));
    }

    @GetMapping(params = {"pickupDate"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getTodayPickups(@RequestParam Date pickupDate){
        return new ResponseUtil(200,"done",resService.getTodayPickups(pickupDate));
    }

    @GetMapping(params = {"returnDate"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getTodayReturning(){
        return new ResponseUtil(200,"done",resService.getTodayReturning());
    }

    @GetMapping
    public  ResponseUtil getAllReservations(){
        return new ResponseUtil(200,"done",resService.getAllReservations());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil setAcceptReservationRequest(@RequestBody ReservationDTO reservationDTO){
        CarDTO carDTO = carService.searchCar(reservationDTO.getCar().getNumber());
        CustomerDTO customerDTO = customerService.searchCustomer(reservationDTO.getCustomer().getCustomerNic());
        reservationDTO.setCar(carDTO);
        reservationDTO.setCustomer(customerDTO);
        resService.updateReservation(reservationDTO);
        return new ResponseUtil(200,"done",null);
    }
}
