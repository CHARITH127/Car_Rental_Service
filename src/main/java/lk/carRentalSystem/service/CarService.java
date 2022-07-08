package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.CarDTO;
import lk.carRentalSystem.dto.CustomerDTO;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO dto);
    void deleteCar(String id);
    void updateCar(CarDTO dto);
    CarDTO searchCar(String id);
    List<CarDTO> getAllCars();
}
