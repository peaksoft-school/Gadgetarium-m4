package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.enums.Role;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String firstName;

    private String lastname;

    private String email;

    private Role role;

    private String phoneNumber;

    private String password;

    private String passwordAdmit;
    private Long basketId;

    private Long chosenId;

    private Long chosenId;

}