package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminLoginVerification extends JpaRepository<Admin ,Integer> {

    @Query(value = "select * from Admin where adminID=?1 and password=?2",nativeQuery = true)
    Admin getAdminObject(String userId ,String password);
}
