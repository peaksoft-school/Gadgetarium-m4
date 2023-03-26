package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponce {
    private Long id;
    private String product;
    private String user;
    private String feedback;
    private String adminReplay;
    private String media;
    private byte productEvaluation;
}
