package us.peaksoft.gadgetarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.peaksoft.gadgetarium.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Integer countFeedbackByProductEvaluation(byte productEvaluation);

    @Query("select avg(productEvaluation) from Feedback ")
    Double rating();

    @Query("select count (productEvaluation) from  Feedback ")
    Long allfeedback();
}