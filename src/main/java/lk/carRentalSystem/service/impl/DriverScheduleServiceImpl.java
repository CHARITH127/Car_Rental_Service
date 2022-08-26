package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.DriverScheduleDTO;
import lk.carRentalSystem.dto.ReservationDTO;
import lk.carRentalSystem.entity.Car;
import lk.carRentalSystem.entity.DriverSchedule;
import lk.carRentalSystem.entity.Reservation;
import lk.carRentalSystem.repo.DriverScheduleRepo;
import lk.carRentalSystem.repo.ReservationRepo;
import lk.carRentalSystem.service.DriverScheduleService;
import lk.carRentalSystem.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DriverScheduleServiceImpl implements DriverScheduleService {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DriverScheduleRepo scheduleRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveDriverSchedule(DriverScheduleDTO dto) {
        scheduleRepo.save(mapper.map(dto, DriverSchedule.class));
    }

    @Override
    public void deleteDriverSchedule(int id) {
        scheduleRepo.deleteById(id);
    }

    @Override
    public void updateDriverSchedule(DriverScheduleDTO dto) {
        scheduleRepo.save(mapper.map(dto, DriverSchedule.class));
    }

    @Override
    public DriverScheduleDTO searchDriverSchedule(int id) {
        return mapper.map(scheduleRepo.findById(id).get(),DriverScheduleDTO.class) ;
    }

    @Override
    public DriverScheduleDTO getScheduleFromReservation(String resID) {
        return mapper.map(scheduleRepo.getDriverScheduleByReservationId(resID),DriverScheduleDTO.class) ;
    }

    @Override
    public List<DriverScheduleDTO> getAllDriverSchedule() {
        return mapper.map(scheduleRepo.findAll(), new TypeToken<List<DriverScheduleDTO>>() {
        }.getType());
    }

    @Override
    public List<DriverScheduleDTO> getAllDriverScheduleByDate(Date date) {
        return mapper.map(scheduleRepo.getDriverScheduleByDate(date), new TypeToken<List<DriverScheduleDTO>>() {
        }.getType());
    }

    @Override
    public List<DriverScheduleDTO> getDriverScheduleByDriver(String driverNic) {
        return mapper.map(scheduleRepo.getDriverScheduleByDriver(driverNic), new TypeToken<List<DriverScheduleDTO>>() {
        }.getType());
    }

    @Override
    public Object getReservationByReservationId(String resId){
        if (scheduleRepo.getDriverScheduleByReservationId(resId)==null) {
            ReservationDTO reservationById = reservationService.getReservationById(resId);
            return reservationById;
        }else {
            DriverSchedule driverSchedule = scheduleRepo.getDriverScheduleByReservationId(resId);
            return mapper.map(driverSchedule,DriverScheduleDTO.class);
        }
    }

    @Override
    public int setOccupiedDrivers() {
        LocalDate date = LocalDate.now();
        List<DriverSchedule> driverSchedules = scheduleRepo.setOccupiedDrivers(Date.valueOf(date));
        int total=0;
        for (DriverSchedule driverSchedule : driverSchedules) {
            total=total+1;
        }
        return total;
    }

    @Override
    public int setAvailableDrivers() {
        LocalDate date = LocalDate.now();
        System.out.println(Date.valueOf(date));
        List<DriverSchedule> driverSchedules = scheduleRepo.setAvailableDrivers(Date.valueOf(date));
        int total=0;
        for (DriverSchedule driverSchedule : driverSchedules) {
            total=total+1;
        }
        return total;
    }

    @Override
    public List<DriverScheduleDTO> getWeeklyDriverScheduleByDriver(String DriverID) {
        LocalDate firstDate = LocalDate.now();
        while (firstDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstDate = firstDate.minusDays(1);
        }
        System.out.println(firstDate);

        LocalDate lastDate = LocalDate.now();
        while (lastDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
            lastDate = lastDate.plusDays(1);
        }

        Date monday = Date.valueOf(firstDate);
        Date sunday = Date.valueOf(lastDate);
        System.out.println(monday + " "+sunday);

        List<DriverSchedule> scheduleList = scheduleRepo.getWeeklyDriverScheduleByDriver(monday, sunday, DriverID);

        return mapper.map(scheduleList, new TypeToken<List<DriverScheduleDTO>>() {
        }.getType());
    }
}
