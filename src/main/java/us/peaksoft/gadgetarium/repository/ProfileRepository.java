package us.peaksoft.gadgetarium.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import us.peaksoft.gadgetarium.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
