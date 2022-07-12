package lk.carRentalSystem.controller;

import lk.carRentalSystem.dto.DriverDTO;
import lk.carRentalSystem.dto.DriverScheduleDTO;
import lk.carRentalSystem.service.DriverScheduleService;
import lk.carRentalSystem.service.DriverService;
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
@RequestMapping("driver")
@CrossOrigin
public class DriverController {

    @Autowired
    DriverService driverService;

    @Autowired
    DriverScheduleService scheduleService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil SaveDriver(@RequestPart("files") MultipartFile[] files, @RequestPart("driver") DriverDTO dto) {

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

        driverService.saveDriver(dto);

        return new ResponseUtil(200, "Save", null);
    }


    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updateDriver(@RequestPart("files") MultipartFile[] files, @RequestPart("driver") DriverDTO dto){
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
        driverService.updateDriver(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @GetMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDriverDetails(@RequestParam String id){
        DriverDTO driverDTO = driverService.searchDriver(id);
        return new ResponseUtil(200, "done", driverDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteDriver(@RequestParam String id){
        driverService.deleteDriver(id);
        return new ResponseUtil(200, "done", null);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseUtil getAllDriverDetails(){
        return new ResponseUtil(200, "Updated", driverService.getAllDriver());
    }

    @GetMapping(params = {"date"})
    public ResponseUtil getTodayDriverSchedule(@RequestParam Date date){
        List<DriverScheduleDTO> schedule = scheduleService.getAllDriverScheduleByDate(date);
        return new ResponseUtil(200, "done", schedule);
    }

}
