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
@Table(name = "order_list")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderList")
    private List<Product> products;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderList")
    private User user;
}