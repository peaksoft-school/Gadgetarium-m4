package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;
import us.peaksoft.gadgetarium.enums.OS;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private Color color;

    private String dateOfIssue;

    @Enumerated(EnumType.STRING)
    private OS os;

    private String ram;
    private String rom;
    private String sim;
    private Long quantityOfSim;
    private String cpu;
    private String weight;
    private String guarantee;
    private String image;
    private String displayInch;
    private String appointment;
    private String PDF;
    private String description;

    @Column(name = "capacity_battery")
    private String capacityBattery;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    Long categoryId;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;


    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;


    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "order_list_id")
    private OrderList orderList;

    @ManyToOne
    @JoinColumn(name = "chosen_id")
    private Chosen chosen;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id")
    private Discount discount;
}