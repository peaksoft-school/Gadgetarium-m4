package us.peaksoft.gadgetarium.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.peaksoft.gadgetarium.entity.Address;
@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
}