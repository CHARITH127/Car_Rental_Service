package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.entity.Customer;
import lk.carRentalSystem.entity.Reservation;
import lk.carRentalSystem.repo.CustomerRepo;
import lk.carRentalSystem.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        } else {
            customerRepo.save(mapper.map(dto, Customer.class));
        }

    }

    @Override
    public void deleteCustomer(String id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        }else {
            throw new RuntimeException("not such a customer");
        }
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getCustomerNic())) {
            customerRepo.save(mapper.map(dto, Customer.class));
        } else {
            throw new RuntimeException("not such a customer");
        }

    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        if (customerRepo.existsById(id)) {
            return mapper.map(customerRepo.findById(id).get(), CustomerDTO.class);
        } else {
            throw new RuntimeException("not such a customer");
        }
    }

    @Override
    public CustomerDTO getCustomerByUserNamePassword(String userName, String password) {
        return mapper.map(customerRepo.getCustomerByNameAndPassword(userName,password),CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapper.map(customerRepo.findAll(),new TypeToken<List<CustomerDTO>>(){}.getType());
    }

}
