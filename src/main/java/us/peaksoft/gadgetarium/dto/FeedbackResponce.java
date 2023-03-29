package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponce {
    private Long id;
    private String product;
    private String user;
    private String firstName;
    private String lastName;
    private String feedback;
    private String adminReplay;
    private String media;
    private byte productEvaluation;
    private Date createdDate;
}
