package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.dto.DriverScheduleDTO;
import lk.carRentalSystem.entity.DriverSchedule;

import java.sql.Date;
import java.util.List;

public interface DriverScheduleService {
    void saveDriverSchedule(DriverScheduleDTO dto);

    void deleteDriverSchedule(int id);

    void updateDriverSchedule(DriverScheduleDTO dto);

    DriverScheduleDTO searchDriverSchedule(int id);

    DriverScheduleDTO getScheduleFromReservation(String resID);

    List<DriverScheduleDTO> getAllDriverSchedule();

    Object getReservationByReservationId(String resId);

    int setOccupiedDrivers();

    int setAvailableDrivers();

    List<DriverScheduleDTO> getAllDriverScheduleByDate(Date date);

    List<DriverScheduleDTO> getDriverScheduleByDriver(String driverNic);

    List<DriverScheduleDTO> getWeeklyDriverScheduleByDriver(String DriverID);
}
