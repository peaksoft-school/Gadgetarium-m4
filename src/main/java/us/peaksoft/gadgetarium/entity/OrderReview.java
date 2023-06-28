package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;
import us.peaksoft.gadgetarium.enums.Review;

import java.util.List;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_review")
public class OrderReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Review review;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orderReview")
    private List<Order>orders;
}
