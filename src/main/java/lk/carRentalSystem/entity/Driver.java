package lk.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Driver {
    @Id
    private String driverNic;
    private String driverName;
    private String driverPassword;
    private String email;
    private String driverAddress;
    private String driverTel;
    private String driverIdCard;
    private String drivingLicense;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<DriverSchedule> driverSchedules;


}
