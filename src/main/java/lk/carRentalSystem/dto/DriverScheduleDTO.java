package lk.carRentalSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.carRentalSystem.entity.Driver;
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
public class DriverScheduleDTO {
    private int scheduleId;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time pick_up_time;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    private Date return_date;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    private Date pick_up_date;
    private DriverDTO driver;
    private ReservationDTO reservationDTO;

}
