package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseForVarietyOfDelivery {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String shipping;
}
