package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.FeedbackRequest;
import us.peaksoft.gadgetarium.dto.FeedbackResponce;

import java.util.List;

public interface FeedbackService {
    List<FeedbackResponce> getAllFeedbacks();

    FeedbackResponce save(FeedbackRequest feedbackRequest);

    FeedbackResponce update(Long id, FeedbackRequest feedbackRequest);

    FeedbackResponce getById(Long id);

    void delete(Long id);
}
