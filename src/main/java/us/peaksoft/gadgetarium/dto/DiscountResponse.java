package us.peaksoft.gadgetarium.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.entity.Product;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {

    private Long id;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private int percent;
}
