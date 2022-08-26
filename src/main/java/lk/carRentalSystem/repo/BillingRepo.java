package lk.carRentalSystem.repo;

import lk.carRentalSystem.entity.Billing;
import lk.carRentalSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface BillingRepo extends JpaRepository<Billing, String> {

    @Query(value = "select billingId from Billing order by billingId desc limit 1", nativeQuery = true)
    String generateBillingId();

    @Query(value = "select * from Billing where billingDate=?", nativeQuery = true)
    List<Billing> getTodayPayments(Date date);

    @Query(value = "select * from Billing where billingDate between ?1 and ?2", nativeQuery = true)
    List<Billing> getWeeklyIncome(Date Monday, Date sunday);

    @Query(value = "select * from Billing where billingDate between ?1 and ?2", nativeQuery = true)
    List<Billing> getMonthlyIncome(Date startDate, Date endDate);

    @Query(value = "select * from Billing where billingDate between ?1 and ?2", nativeQuery = true)
    List<Billing> getYearlyIncome(Date yearFirstDate, Date yearLastDate);

    @Query(value = "select * from Billing where billingDate=?", nativeQuery = true)
    List<Billing> getPaymentsOnSpecific(Date date);

}
