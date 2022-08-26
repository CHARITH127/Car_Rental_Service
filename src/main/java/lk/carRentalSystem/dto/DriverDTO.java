package lk.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverDTO {
    private String driverNic;
    private String driverName;
    private String driverPassword;
    private String email;
    private String driverAddress;
    private String driverTel;
    private String driverIdCard;
    private String drivingLicense;
}
