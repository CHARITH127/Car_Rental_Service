package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.CarDTO;
import lk.carRentalSystem.entity.Billing;
import lk.carRentalSystem.entity.Car;
import lk.carRentalSystem.repo.CarRepo;
import lk.carRentalSystem.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveCar(CarDTO dto) {
        if (carRepo.existsById(dto.getNumber())) {
            throw new RuntimeException("Customer All ready saved");
        } else {
            carRepo.save(mapper.map(dto, Car.class));
        }
    }

    @Override
    public void deleteCar(String id) {
        if (carRepo.existsById(id)) {
            carRepo.deleteById(id);
        } else {
            throw new RuntimeException("not such a customer");
        }
    }

    @Override
    public void updateCar(CarDTO dto) {
        if (carRepo.existsById(dto.getNumber())) {
            carRepo.save(mapper.map(dto, Car.class));
        } else {
            throw new RuntimeException("not such a customer");
        }

    }

    @Override
    public CarDTO searchCar(String id) {
        if (carRepo.existsById(id)) {
            return mapper.map(carRepo.findById(id).get(), CarDTO.class);
        } else {
            throw new RuntimeException("not such a customer");
        }
    }

    @Override
    public int checkTodayAvailableCars() {
        LocalDate date = LocalDate.now();
        List<Car> cars = carRepo.checkTodayAvailableCars(Date.valueOf(date));
        int total=0;
        for (Car car : cars) {
            total=total+1;
        }
        return total;
    }

    @Override
    public int checkTodayOccupiedCars() {
        LocalDate date = LocalDate.now();
        List<Car> cars = carRepo.checkTodayOnBooking(Date.valueOf(date));
        int total=0;
        for (Car car : cars) {
            total=total+1;
        }
        return total;
    }

    @Override
    public List<CarDTO> getAllCars() {
        return mapper.map(carRepo.findAll(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> checkCarAvailability(Date pick_up_date, Date return_date) {
        return mapper.map(carRepo.checkCarAvailability(pick_up_date, return_date), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> checkDamagedCars() {
        return mapper.map(carRepo.checkDamagedCars(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> getSortedCars(String transmissionType, String brand, String type, String fuelType, int numberOfPassengers) {
        return mapper.map(carRepo.sortCarsByAttributes(transmissionType,brand,type,fuelType,numberOfPassengers), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> checkTodayOnBooking(Date todayDate) {
        return mapper.map(carRepo.checkTodayOnBooking(todayDate), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> checkNeedToMaintain() {
        return mapper.map(carRepo.checkNeedToMaintain(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }
}
