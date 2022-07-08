package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.dto.DriverDTO;
import lk.carRentalSystem.entity.Customer;
import lk.carRentalSystem.entity.Driver;
import lk.carRentalSystem.repo.DriverRepo;
import lk.carRentalSystem.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveDriver(DriverDTO dto) {
        if (repo.existsById(dto.getDriverNic())) {
            throw new RuntimeException("Customer All ready saved");
        } else {
            repo.save(mapper.map(dto, Driver.class));
        }
    }

    @Override
    public void deleteDriver(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("not such a customer");
        }
    }

    @Override
    public void updateDriver(DriverDTO dto) {
        if (repo.existsById(dto.getDriverNic())) {
            repo.save(mapper.map(dto, Driver.class));
        } else {
            throw new RuntimeException("not such a customer");
        }
    }

    @Override
    public DriverDTO searchDriver(String id) {
        if (repo.existsById(id)) {
            return mapper.map(repo.findById(id).get(), DriverDTO.class);
        } else {
            throw new RuntimeException("not such a customer");
        }
    }

    @Override
    public List<DriverDTO> getAllDriver() {
        return mapper.map(repo.findAll(), new TypeToken<List<DriverDTO>>() {
        }.getType());
    }
}
