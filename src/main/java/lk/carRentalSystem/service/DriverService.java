package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.dto.DriverDTO;
import lk.carRentalSystem.dto.DriverScheduleDTO;
import lk.carRentalSystem.entity.Driver;
import lk.carRentalSystem.entity.DriverSchedule;
import lk.carRentalSystem.entity.Reservation;

import java.sql.Date;
import java.util.List;

public interface DriverService {
    void saveDriver(DriverDTO dto);
    void deleteDriver(String id);
    void updateDriver(DriverDTO dto);
    DriverDTO searchDriver(String id);
    DriverDTO getRandomDriver(Date pick_up_date, Date return_date);
    List<DriverDTO> getAllDriver();
    List<DriverDTO> getOccupiedDriversToday(Date today_date);
    List<DriverDTO> getAvailableDriversToday(Date today_date);

}
