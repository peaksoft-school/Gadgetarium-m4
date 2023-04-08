package us.peaksoft.gadgetarium.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT COUNT(u) FROM Product u WHERE u.brand=:brand and u.color=:color and u.ram=:ram and u.quantityOfSim=:sim and u.price=:price")
    Long Quantity(@Param("brand") Brand brand, @Param("color") Color color, @Param("ram") String ram, @Param("sim") Long sim, @Param("price") int price);

    @Query("SELECT p FROM Product p join p.category cat where upper(cat.name) like concat('%', :text, '%') " +
            "or upper(p.name) like concat('%', :text, '%')" +
            " or cast(upper(p.brand) as string) like concat('%', :text, '%') or upper(p.description) like concat('%', :text, '%')")
    List<Product>searchProductAndPagination(@Param("text") String text, Pageable pageable);
}