package lk.carRentalSystem.entity;

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
public class DriverSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleId;

    private Date pick_up_date;
    private Date return_date;
    private Time pick_up_time;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Reservation reservation;


}
