package us.peaksoft.gadgetarium.dto;

import lombok.*;
import us.peaksoft.gadgetarium.entity.Address;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Long id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Address;
    private String PhoneNumber;

    // getters and setters
}