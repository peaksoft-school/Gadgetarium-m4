package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import jdk.jfr.SettingDefinition;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_start")
    private LocalDate dateOfStart;

    @Column(name = "date_of_finish")
    private LocalDate dateOfFinish;

    private int percent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discount")
    private List<Product> products;


}
