package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.dto.DriverScheduleDTO;

import java.sql.Date;
import java.util.List;

public interface DriverScheduleService {
    void saveDriverSchedule(DriverScheduleDTO dto);

    void deleteDriverSchedule(int id);

    void updateDriverSchedule(DriverScheduleDTO dto);

    DriverScheduleDTO searchDriverSchedule(int id);

    DriverScheduleDTO getScheduleFromReservation(String resID);

    List<DriverScheduleDTO> getAllDriverSchedule();

    List<DriverScheduleDTO> getAllDriverScheduleByDate(Date date);
}
