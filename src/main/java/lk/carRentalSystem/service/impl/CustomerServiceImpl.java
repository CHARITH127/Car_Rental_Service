package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.entity.Customer;
import lk.carRentalSystem.entity.Reservation;
import lk.carRentalSystem.repo.CustomerRepo;
import lk.carRentalSystem.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getCustomerNic())) {
            throw new RuntimeException("Customer All ready saved");
        }else {
            customerRepo.save(mapper.map(dto,Customer.class));
        }

    }

    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getCustomerNic())) {
            customerRepo.save(mapper.map(dto,Customer.class));
        }else {
            throw new RuntimeException("Customer All ready saved");
        }

    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return null;
    }

}
