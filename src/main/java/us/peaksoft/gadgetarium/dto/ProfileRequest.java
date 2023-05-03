package us.peaksoft.gadgetarium.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
}