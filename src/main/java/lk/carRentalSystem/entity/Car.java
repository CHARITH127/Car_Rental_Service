package lk.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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
    private double lossPayment;
    private String maintainStatus;
    private String damageStatus;
    private double extraKilometerPrice;
    private String color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private CarImageDetails imageDetails;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
