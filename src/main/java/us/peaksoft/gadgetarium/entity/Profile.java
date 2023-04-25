package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "profiles")
public class Profile {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String deliveryAddress;
        private String oldPassword;
        private String newPassword;
        @Transient
        private String confirmPassword;


}
