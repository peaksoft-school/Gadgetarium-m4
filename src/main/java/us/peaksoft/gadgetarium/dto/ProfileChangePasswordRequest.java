package us.peaksoft.gadgetarium.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileChangePasswordRequest {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}