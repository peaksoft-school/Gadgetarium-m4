package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.enums.Role;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToOne
    @JoinColumn(name = "chosen_id")
    private Chosen chosen;

    @OneToOne
    @JoinColumn(name = "order_list_id")
    private OrderList orderList;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "payments_id")
    private Payments payments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders;
}