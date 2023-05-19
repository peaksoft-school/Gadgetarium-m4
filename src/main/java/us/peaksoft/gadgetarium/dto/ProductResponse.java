package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.File;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;
import us.peaksoft.gadgetarium.enums.OS;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private int price;

    private double currentPrice;

    private Brand brand;

    private Color color;

    private String dateOfIssue;

    private OS os;

    private String ram;

    private File PDF;
    private String rom;

    private String sim;

    private Long quantityOfSim;

    private String cpu;

    private String weight;

    private String guarantee;

    private String image;

    private String displayInch;

    private String appointment;

    private String capacityBattery;

    private String description;

    private Long quantityOfProducts;

    private int disPercent;

    private String categoryName;

    private Boolean inBasket;
}