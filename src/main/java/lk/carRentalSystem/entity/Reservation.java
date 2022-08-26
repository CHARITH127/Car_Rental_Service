package lk.carRentalSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Reservation {

    @Id
    private String reservation_id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn
    private Car car;

    private Date reserve_date;

    private Date pick_up_date;

    private Date return_date;

    private Time pick_up_time;

    private String pick_up_and_return_venue;

    private String reservation_status;

    private String driverStatus;

    private String bankSlip;

}
