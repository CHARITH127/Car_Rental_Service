package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer ,String> {
    
}
