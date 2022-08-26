package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.CustomerDTO;
import lk.carRentalSystem.entity.Reservation;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO dto);
    void deleteCustomer(String id);
    void updateCustomer(CustomerDTO dto);
    CustomerDTO searchCustomer(String id);
    CustomerDTO getCustomerByUserNamePassword(String userId ,String password);
    List<CustomerDTO> getAllCustomers();
}
