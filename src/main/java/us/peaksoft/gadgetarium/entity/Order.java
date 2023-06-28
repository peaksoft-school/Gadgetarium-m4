package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import us.peaksoft.gadgetarium.enums.OrderStatus;
import us.peaksoft.gadgetarium.enums.PaymentType;
import us.peaksoft.gadgetarium.enums.Shipping;


@Setter
@Data
@NoArgsConstructor
@Getter
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
