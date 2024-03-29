package lk.carRentalSystem.dto;

import lk.carRentalSystem.entity.CarImageDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarDTO {
    private String number;
    private String brand;
    private String type;
    private int numberOfPassengers;
    private String transmissionType;
    private String fuelType;
    private double dailyRate;
    private double monthlyRate;
    private long mileage;
    private double lossPayment;
    private String maintainStatus;
    private String damageStatus;
    private double extraKilometerPrice;
    private String color;

    private CarImageDetailsDTO imageDetails;
}
