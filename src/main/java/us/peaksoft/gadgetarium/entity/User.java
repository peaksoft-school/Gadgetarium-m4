package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import us.peaksoft.gadgetarium.enums.Role;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString
public class User implements UserDetails {
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

    @Column(name = "phoneNumber")
    private String phoneNumber;
    private LocalDate createdDate;

    @OneToOne
    @JoinColumn(name = "payments_id")
    private Payments payments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Feedback> feedbacks;
}
