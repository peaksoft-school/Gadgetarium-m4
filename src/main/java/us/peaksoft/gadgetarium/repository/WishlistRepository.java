package us.peaksoft.gadgetarium.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import us.peaksoft.gadgetarium.entity.Chosen;
import us.peaksoft.gadgetarium.entity.Product;

import java.util.List;

@Repository
@Transactional
public interface WishlistRepository extends JpaRepository<Chosen, Long> {

    @Query("SELECT pro FROM Product pro JOIN pro.chosen cho WHERE cho.id =:id")
    List<Product> getProductsByChosenId(@PathVariable("id") Long id, Pageable pageable);

}
