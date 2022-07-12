package lk.carRentalSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.carRentalSystem.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BillingDTO {
    private String billingId;
    private ReservationDTO reservation;

    private double loosePayment;
    private double fullPayment;
    private double damagePayment;
    private double driverPayment;
    private double refundPayment;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    private Date billingDate;
}
