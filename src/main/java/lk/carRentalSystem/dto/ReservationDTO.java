package lk.carRentalSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.carRentalSystem.entity.Car;
import lk.carRentalSystem.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReservationDTO {
    private String reservation_id;

    private CustomerDTO customer;
    private CarDTO car;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    private Date reserve_date;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    private Date pick_up_date;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    private Date return_date;
    @JsonFormat(pattern = "HH:mm:ss")
    private Time pick_up_time;

    private String pick_up_and_return_venue;

    private String reservation_status;

    private String driverStatus;

    private String bankSlip;
}
