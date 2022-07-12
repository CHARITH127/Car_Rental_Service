package lk.carRentalSystem.controller;

import lk.carRentalSystem.dto.CarDTO;
import lk.carRentalSystem.service.CarService;
import lk.carRentalSystem.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("car")
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil saveCar(@RequestPart("files") MultipartFile[] files, @RequestPart("car") CarDTO dto) {

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
        System.out.println(dto);
        carService.saveCar(dto);

        return new ResponseUtil(200, "Save", null);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil UpdateCar(@RequestPart("files") MultipartFile[] files, @RequestPart("car") CarDTO dto) {

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
        carService.updateCar(dto);

        return new ResponseUtil(200, "Updated", null);
    }

    @GetMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCarDetails(@RequestParam String id){
        CarDTO carDTO = carService.searchCar(id);
        return new ResponseUtil(200, "done", carDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCar(@RequestParam String id){
        carService.deleteCar(id);
        return new ResponseUtil(200, "done", null);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseUtil getAllCarDetails(){
        return new ResponseUtil(200, "done", carService.getAllCars());
    }

    @GetMapping(params = {"pick_up_date","return_date"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil checkCarAvailability(@RequestParam("pick_up_date") Date pick_up_date,@RequestParam("return_date")Date return_date){
        List<CarDTO> carDTOS = carService.checkCarAvailability(pick_up_date, return_date);
        return new ResponseUtil(200, "done", carDTOS);
    }

    @GetMapping(params = {"damageCars"})
    public ResponseUtil checkDamagedCars(){
        List<CarDTO> carDTOS = carService.checkDamagedCars();
        return new ResponseUtil(200, "done", carDTOS);
    }

    @GetMapping(params = {"todayDate"})
    public ResponseUtil checkTodayOnBooking(@RequestParam Date todayDate){
        List<CarDTO> carDTOS = carService.checkTodayOnBooking(todayDate);
        return new ResponseUtil(200, "done", carDTOS);
    }

    @GetMapping(params = {"needToMaintain"})
    public ResponseUtil checkNeedToMaintain(){
        List<CarDTO> carDTOS = carService.checkNeedToMaintain();
        return new ResponseUtil(200, "done", carDTOS);
    }

}
