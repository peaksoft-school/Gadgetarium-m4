package us.peaksoft.gadgetarium.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.peaksoft.gadgetarium.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

}