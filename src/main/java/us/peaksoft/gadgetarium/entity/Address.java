package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String cityName;
    private String streetName;
    private String stateName;
    private String postalCode;
    private String countryName;
}