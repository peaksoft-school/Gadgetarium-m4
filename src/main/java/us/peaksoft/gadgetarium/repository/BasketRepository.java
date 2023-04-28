package us.peaksoft.gadgetarium.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import us.peaksoft.gadgetarium.entity.Basket;
import us.peaksoft.gadgetarium.entity.Product;
import java.util.List;

@Repository
@Transactional
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query("SELECT p FROM Product p JOIN p.basket basket WHERE basket.id=:id")
    List<Product> getProductsByBasketId(@Param("id") Long id, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.basket basket WHERE basket.id=:id")
    Product getProducts(@PathVariable("id") Long id);
}