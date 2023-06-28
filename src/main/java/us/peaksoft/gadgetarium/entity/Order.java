package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;
import us.peaksoft.gadgetarium.enums.OrderStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count_of_product")
    private Short countOfProducts;

    @Column(name = "total_sum")
    private int totalSum;

    private String shipping;

    @Column(name = "type_payment")
    private String typePayment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private DeliveryMen deliveryMan;
}
