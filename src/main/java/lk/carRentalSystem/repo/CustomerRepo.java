package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepo extends JpaRepository<Customer ,String> {

    @Query(value = "select * from Customer where customerNic=?1 and customerPassword=?2",nativeQuery = true)
    public Customer getCustomerByNameAndPassword(String customerId ,String password);
    
}
