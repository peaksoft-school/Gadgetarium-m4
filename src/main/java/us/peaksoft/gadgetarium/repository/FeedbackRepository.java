package us.peaksoft.gadgetarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.peaksoft.gadgetarium.entity.Feedback;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}