package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.BillingDTO;
import lk.carRentalSystem.dto.ReservationDTO;
import lk.carRentalSystem.entity.Billing;
import lk.carRentalSystem.repo.BillingRepo;
import lk.carRentalSystem.service.BillingService;
import lk.carRentalSystem.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.*;

@Service
@Transactional
public class BillingServiceImpl implements BillingService {

    @Autowired
    BillingRepo billingRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public String generateBillingId() {
        String result = billingRepo.generateBillingId();
        System.out.println(result);
        String id;

        if (result==null) {
            id = "B-001";
            return id;
        } else {
            int tempID = Integer.parseInt(result.split("-")[1]);
            tempID = tempID + 1;
            if (tempID < 9) {
                id = "B-00" + tempID;
                return id;
            } else if (tempID < 99) {
                id = "B-0" + tempID;
                return id;
            } else {
                id = "B-" + tempID;
                return id;
            }
        }

    }

    @Override
    public void saveThePayment(BillingDTO billingDTO) {
        if (billingRepo.existsById(billingDTO.getBillingId())) {
            throw new RuntimeException("Billing Id is already insert");
        }else {
            billingRepo.save(mapper.map(billingDTO, Billing.class));
        }
    }

    @Override
    public BillingDTO getBillingDetails(String bilId) {
        if (billingRepo.existsById(bilId)) {
            return mapper.map(billingRepo.findById(bilId),BillingDTO.class);
        }else {
            throw new RuntimeException("Not Such a Bill");
        }
    }

    @Override
    public Double getTodayIncome(Date date) {
        double total=0;
        List<Billing> payments = billingRepo.getTodayPayments(date);
        for (Billing payment : payments) {
            total+=payment.getFullPayment();
        }
        return total;
    }

    @Override
    public Double getWeeklyIncome() {
        double total=0;
        LocalDate firstDate = LocalDate.now();
        while (firstDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstDate = firstDate.minusDays(1);
        }
        System.out.println(firstDate);

        LocalDate lastDate = LocalDate.now();
        while (lastDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
            lastDate = lastDate.plusDays(1);
        }

        Date monday = Date.valueOf(firstDate);
        Date sunday = Date.valueOf(lastDate);
        System.out.println(monday + " "+sunday);
        List<Billing> billings = billingRepo.getWeeklyIncome(monday, sunday);

        for (Billing billing : billings) {
            total+=billing.getFullPayment();
        }
        return total;
    }

    @Override
    public Double getMonthlyIncome() {
        double total=0;
        LocalDate initial = LocalDate.now();
        LocalDate start = initial.with(firstDayOfMonth());
        LocalDate end = initial.with(lastDayOfMonth());
        Date startDate = Date.valueOf(start);
        Date endDate = Date.valueOf(end);
        System.out.println(startDate+" "+endDate);
        List<Billing> billings = billingRepo.getMonthlyIncome(startDate, endDate);

        for (Billing billing : billings) {
            total+=billing.getFullPayment();
        }
        return total;
    }

    @Override
    public Double getYearlyIncome() {
        double total=0;
        LocalDate initial = LocalDate.now();
        LocalDate start = initial.with(firstDayOfYear());
        LocalDate end = initial.with(lastDayOfYear());
        Date startDate = Date.valueOf(start);
        Date endDate = Date.valueOf(end);
        System.out.println(startDate+" "+endDate);
        List<Billing> billings = billingRepo.getYearlyIncome(startDate, endDate);

        for (Billing billing : billings) {
            total+=billing.getFullPayment();
        }
        return total;
    }

    @Override
    public Double getPaymentsOnSpecific(Date date) {
        double total=0;
        List<Billing> payments = billingRepo.getPaymentsOnSpecific(date);
        for (Billing payment : payments) {
            total+=payment.getFullPayment();
        }
        return total;
    }

    @Override
    public void deleteBill(String bilId) {
        if (billingRepo.existsById(bilId)) {
            billingRepo.deleteById(bilId);
        }else {
            throw new RuntimeException("Not Such a Bill");
        }
    }

    @Override
    public List<BillingDTO> deleteAllBill() {
        return mapper.map(billingRepo.findAll(), new TypeToken<List<BillingDTO>>() {
        }.getType());
    }


    @Override
    public double getTodayReservation(Date date) {
        return 0;
    }
}
