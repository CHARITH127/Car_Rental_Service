package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.Customer;
import lk.carRentalSystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer ,String> {
}
