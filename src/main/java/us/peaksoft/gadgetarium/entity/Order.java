package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.enums.OrderStatus;
import us.peaksoft.gadgetarium.enums.PaymentType;
import us.peaksoft.gadgetarium.enums.Shipping;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count_of_product")
    private Short countOfProducts;

    @Column(name = "total_sum")
    private int totalSum;

    @Enumerated(EnumType.STRING)
    private PaymentType typePayment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private DeliveryMen deliveryMan;

    @ManyToOne
    @JoinColumn(name = "order_review_status")
    private OrderReview orderReview;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Enumerated(EnumType.STRING)
    private Shipping shipping;
}
