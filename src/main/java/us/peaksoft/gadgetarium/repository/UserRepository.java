package us.peaksoft.gadgetarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.peaksoft.gadgetarium.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}