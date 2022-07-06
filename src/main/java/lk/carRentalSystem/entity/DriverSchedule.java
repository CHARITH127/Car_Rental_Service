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
public class DriverSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driverID")
    private Driver driver;

    private Date check_in;
    private Date check_out;
}
