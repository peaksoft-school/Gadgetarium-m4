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
public class FeedbackRequest {
    private Product product;
    private User user;
    private String feedback;
    private String media;
    private byte productEvaluation;

}
