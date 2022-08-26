package lk.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class CarImageDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    private String frontImage;
    private String backImage;
    private String sideImage;
    private String interiorImage;

    /*@OneToOne(mappedBy = "imageDetails")
    private Car car;*/
}
