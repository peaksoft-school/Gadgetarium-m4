package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.FeedbackRequest;
import us.peaksoft.gadgetarium.dto.FeedbackResponce;
import us.peaksoft.gadgetarium.dto.RatingResponce;
import us.peaksoft.gadgetarium.dto.SimpleResponse;

import java.util.List;

public interface FeedbackService {
    List<FeedbackResponce> getAllFeedbacks();

    SimpleResponse save(FeedbackRequest feedbackRequest);

    FeedbackResponce update(Long id, FeedbackRequest feedbackRequest);

    FeedbackResponce getById(Long id);

    SimpleResponse delete(Long id);

    FeedbackResponce reply(Long id, FeedbackRequest feedbackRequest);

    RatingResponce rating();
}
