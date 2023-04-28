package us.peaksoft.gadgetarium.dto;

import lombok.*;
import us.peaksoft.gadgetarium.entity.Address;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String FirstName;
    private String lastName;
    private String email;
    private String Address;
    private String phoneNumber;

    // getters and setters
}