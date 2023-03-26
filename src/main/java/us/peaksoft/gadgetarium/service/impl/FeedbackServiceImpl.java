package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.FeedbackRequest;
import us.peaksoft.gadgetarium.dto.FeedbackResponce;
import us.peaksoft.gadgetarium.entity.Feedback;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.repository.FeedbackRepository;
import us.peaksoft.gadgetarium.service.FeedbackService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackResponce> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        List<FeedbackResponce> feedbackResponceList = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            feedbackResponceList.add(mapToResponse(feedback));
        }
        return feedbackResponceList;
    }

    @Override
    public FeedbackResponce save(FeedbackRequest feedbackRequest) {
        return null;
    }

    @Override
    public FeedbackResponce update(Long id, FeedbackRequest feedbackRequest) {
        return null;
    }

    @Override
    public FeedbackResponce getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
    public FeedbackResponce mapToResponse(Feedback feedback){
        FeedbackResponce feedbackResponce = new FeedbackResponce();
        feedbackResponce.setId(feedback.getId());
        feedbackResponce.setFeedback(feedback.getFeedback());
        feedbackResponce.setProductEvaluation(feedback.getProductEvaluation());
        feedbackResponce.setMedia(feedback.getMedia());
        feedbackResponce.setAdminReplay(feedback.getAdminReplay());
        feedbackResponce.setProduct(feedback.getProduct().getName());
        feedbackResponce.setUser(feedback.getUser().getLastName());
        return feedbackResponce;
    }
}
