package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;
import us.peaksoft.gadgetarium.enums.Subcat;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Subcat subcat;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> products;
}