package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactRequest {
    private String name;
    private String username;
    private String number;
    private String email;
    private String message;

}
