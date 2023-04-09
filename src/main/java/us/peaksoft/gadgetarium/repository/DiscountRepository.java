package us.peaksoft.gadgetarium.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;
import us.peaksoft.gadgetarium.entity.Discount;
import us.peaksoft.gadgetarium.entity.Product;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT p FROM Product p JOIN p.discount discount WHERE discount.id=:id")
    List<Product> getProductsByDiscountId(@PathVariable("id") Long id, Pageable pageable);
}