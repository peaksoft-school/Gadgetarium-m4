package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.entity.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {
    private String FirstName;
    private String LastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private String oldPassword;
    private String newPassword;

    // getters and setters
}