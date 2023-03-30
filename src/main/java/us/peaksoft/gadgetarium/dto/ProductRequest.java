package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;
import us.peaksoft.gadgetarium.enums.OS;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private Brand brand;
    private int price;
    private Color color;
    private String dateOfIssue;
    private OS os;
    private String ram;
    private String rom;
    private String sim;
    private Long quantityOfSim;
    private String cpu;
    private String weight;
    private String guarantee;
    private String displayInch;
    private String appointment;
    private String capacityBattery;
    private String image;
    private String PDF;
    private String description;
    private Long categoryId;
}
