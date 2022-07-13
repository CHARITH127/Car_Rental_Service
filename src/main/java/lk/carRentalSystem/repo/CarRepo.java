package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface CarRepo extends JpaRepository<Car,String> {
    @Query(value = "select * from Car where number not in (select car_number from Reservation where (pick_up_date between ?1 and ?2) or (return_date between ?1 and ?2) and reservation_status='pending' and maintainStatus='No' and damageStatus='No')", nativeQuery = true)
    List<Car> checkCarAvailability(Date pick_up_date, Date return_date);

    @Query(value = "select * from Car where damageStatus='Yes')", nativeQuery = true)
    List<Car> checkDamagedCars();

    @Query(value = "select * from Car where number in (select car_number from Reservation where ? between pick_up_date and return_date", nativeQuery = true)
    List<Car> checkTodayOnBooking(Date todayDate);

    @Query(value = "select * from Car where mileage % 5000 = 0 and maintainStatus='No'", nativeQuery = true)
    List<Car> checkNeedToMaintain();

    @Query(value = "SELECT c FROM Car c WHERE (:transmissionType is null OR c.transmissionType=:transmissionType) AND (:brand is null OR c.brand=:brand) AND (:fuelType is null OR c.fuelType=:fuelType) AND (:type is null OR c.type=:type) AND (:nnumberOfPassengers = 0 OR c.numberOfPassengers=:nnumberOfPassengers)")
    List<Car>sortCarsByAttributes(@Param("transmissionType") String transmissionType, @Param("brand") String brand,@Param("type") String type, @Param("fuelType") String fuelType, @Param("numberOfPassengers") int numberOfPassengers);

}
