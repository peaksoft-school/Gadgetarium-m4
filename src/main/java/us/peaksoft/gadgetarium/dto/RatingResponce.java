package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponce {
    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private double rating;
    private long allFeedback;
}
