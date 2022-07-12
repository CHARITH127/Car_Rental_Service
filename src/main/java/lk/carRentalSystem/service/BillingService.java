package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.BillingDTO;
import lk.carRentalSystem.entity.Billing;

import java.sql.Date;
import java.util.List;

public interface BillingService {

    String generateBillingId();

    void saveThePayment(BillingDTO  billingDTO);

    BillingDTO getBillingDetails(String bilId);

    Double getTodayIncome(Date date);

    Double getWeeklyIncome();

    Double getMonthlyIncome();

    Double getYearlyIncome();

    Double getPaymentsOnSpecific(Date date);

    void deleteBill(String bilId);

    List<BillingDTO> deleteAllBill();

    double getTodayReservation(Date date);
}
