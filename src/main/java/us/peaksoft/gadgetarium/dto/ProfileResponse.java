package us.peaksoft.gadgetarium.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
}