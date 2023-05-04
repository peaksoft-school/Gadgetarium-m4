package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.enums.Color;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonResponse {
    private Long id;
    private String image;
    private String name;
    private Color color;
    private String sim;
    private String ram;
    private String rom;
    private Long quantityOfSim;
    private int price;
}
