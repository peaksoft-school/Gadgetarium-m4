package us.peaksoft.gadgetarium.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_start")
    private LocalDate dateOfStart;

    @Column(name = "date_of_finish")
    private LocalDate dateOfFinish;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "news")
    private List<Product> products;
}