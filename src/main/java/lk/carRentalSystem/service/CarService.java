package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.CarDTO;
import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.entity.Car;

import java.sql.Date;
import java.util.List;

public interface CarService {
    void saveCar(CarDTO dto);
    void deleteCar(String id);
    void updateCar(CarDTO dto);
    CarDTO searchCar(String id);
    int checkTodayAvailableCars();
    int checkTodayOccupiedCars();
    List<CarDTO> getAllCars();
    List<CarDTO> checkCarAvailability(Date pick_up_date,Date return_date);
    List<CarDTO> checkDamagedCars();
    List<CarDTO> getSortedCars(String transmissionType, String brand,String type,String fuelType, int numberOfPassengers);
    List<CarDTO> checkTodayOnBooking(Date todayDate);
    List<CarDTO> checkNeedToMaintain();
}
