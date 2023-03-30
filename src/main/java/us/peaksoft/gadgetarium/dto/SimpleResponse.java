package us.peaksoft.gadgetarium.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponse {
    private HttpStatus httpStatus;
    private String message;
}
