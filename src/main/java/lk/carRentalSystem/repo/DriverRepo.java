package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.Car;
import lk.carRentalSystem.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface DriverRepo extends JpaRepository<Driver,String> {

    @Query(value = "select * from Driver where driverNic not in (select driver_driverNic from DriverSchedule where (pick_up_date between ?1 and ?2) or (return_date between ?1 and ?2)) order by RAND() desc Limit 1",nativeQuery = true)
    Driver getRandomDriver(Date pick_up_date, Date return_date);

    @Query(value = "select * from Driver where driverNic  in (select driver_driverNic from DriverSchedule where (? between pick_up_date and return_date))", nativeQuery = true)
    List<Driver> getOccupiedDriversToday(Date today_date);

    @Query(value = "select * from Driver where driverNic not in (select driver_driverNic from DriverSchedule where (? between pick_up_date and return_date))", nativeQuery = true)
    List<Driver> getAvaulableDriversToday(Date today_date);
}
