package lk.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String customerNic;
    private String customerName;
    private String customerPassword;
    private String email;
    private String customerAddress;
    private String customerTel;
    private String idCard;
    private String drivingLicense;
}
