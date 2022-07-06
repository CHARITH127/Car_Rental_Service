package lk.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Car {
    @Id
    private String number;
    private String brand;
    private String type;
    private int numberOfPassengers;
    private String transmissionType;
    private String fuelType;
    private double dailyRate;
    private double monthlyRate;
    private long mileage;
    private double extraKilometerPrice;
    private String color;
}
