package lk.carRentalSystem.dto;

import lk.carRentalSystem.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarImageDetailsDTO {
    private int id;
    private String frontImage;
    private String backImage;
    private String sideImage;
    private String interiorImage;

}
