package lk.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Billing {
    @Id
    private String billingId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private double loosePayment;
    private double fullPayment;
    private double damagePayment;
    private double driverPayment;
    private double refundPayment;
    private Date billingDate;
}
