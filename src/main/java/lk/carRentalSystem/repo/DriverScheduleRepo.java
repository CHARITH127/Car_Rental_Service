package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.DriverSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface DriverScheduleRepo extends JpaRepository<DriverSchedule,Integer> {
    @Query(value = "select * from DriverSchedule where reservation_reservation_id=?",nativeQuery = true)
    DriverSchedule getDriverScheduleByReservationId(String resID);

    @Query(value = "select * from DriverSchedule where ? between pick_up_date and return_date",nativeQuery = true)
    List<DriverSchedule> getDriverScheduleByDate(Date today_date);

    @Query(value = "select * from DriverSchedule where driver_driverNic=?" , nativeQuery = true)
    List<DriverSchedule> getDriverScheduleByDriver(String driverNic);
}