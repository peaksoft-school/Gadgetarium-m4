package us.peaksoft.gadgetarium.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import us.peaksoft.gadgetarium.entity.Wishlist;
import us.peaksoft.gadgetarium.entity.Product;

import java.util.List;

@Repository
@Transactional
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT pro FROM Product pro JOIN pro.wishlist cho WHERE cho.id =:id")
    List<Product> getProductsByChosenId(@PathVariable("id") Long id, Pageable pageable);

}
