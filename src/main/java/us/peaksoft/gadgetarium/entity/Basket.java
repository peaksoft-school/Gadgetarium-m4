package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
    private List<Product> products;

    @Column(name = "sum")
    private int sum;

    @Column(name = "discounted_difference")
    private int disPercentSum;

    @Column(name = "quantity_of_products")
    private int quantityOfProducts;

    @Column(name = "end_sum")
    private int endSum;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Order order;

}