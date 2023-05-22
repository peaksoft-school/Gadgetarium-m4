package us.peaksoft.gadgetarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.peaksoft.gadgetarium.entity.Order;
@Repository

public interface OrderRepository extends JpaRepository<Order, Long> {
}