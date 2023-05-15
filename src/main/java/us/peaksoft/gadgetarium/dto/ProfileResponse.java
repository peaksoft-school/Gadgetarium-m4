package us.peaksoft.gadgetarium.dto;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String FirstName;
    private String LastName;
    private String Email;
    private String Address;
    private String PhoneNumber;
}