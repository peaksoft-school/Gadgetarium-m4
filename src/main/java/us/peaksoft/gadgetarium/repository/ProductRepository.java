package us.peaksoft.gadgetarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.peaksoft.gadgetarium.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}