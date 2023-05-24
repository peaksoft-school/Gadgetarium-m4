package us.peaksoft.gadgetarium.repository;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.peaksoft.gadgetarium.entity.OrderReview;

import java.util.Optional;

@Repository
public interface OrderReviewRepository extends JpaRepository<OrderReview, Long> {
    @Query("SELECT o FROM OrderReview o WHERE CAST(o.review AS string) = :orderReview")
    Optional<OrderReview> findByOrderReview(@PathParam("orderReview") String orderReview);
}