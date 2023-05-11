package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
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
    private Integer sum;

    @Column(name = "discounted_difference")
    private Integer disPercentSum;

    public int getDisPercentSum() {
        return disPercentSum;
    }

    public void setDisPercentSum(int disPercentSum) {
        this.disPercentSum = disPercentSum;
    }

    @Column(name = "quantity_of_products")
    private Integer quantityOfProducts;

    @Column(name = "end_sum")
    private Integer endSum;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}