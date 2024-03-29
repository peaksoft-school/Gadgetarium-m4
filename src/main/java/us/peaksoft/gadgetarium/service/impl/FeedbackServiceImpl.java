package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.FeedbackRequest;
import us.peaksoft.gadgetarium.dto.FeedbackResponce;
import us.peaksoft.gadgetarium.dto.RatingResponce;
import us.peaksoft.gadgetarium.dto.SimpleResponse;
import us.peaksoft.gadgetarium.entity.Feedback;
import us.peaksoft.gadgetarium.repository.FeedbackRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.service.FeedbackService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
    public SimpleResponse save(FeedbackRequest feedbackRequest) {
        Feedback feedback1 = mapToEntity(feedbackRequest);
        feedbackRepository.save(feedback1);
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setHttpStatus(HttpStatus.OK);
        simpleResponse.setMessage("Ваш отзыв успешно отправлен");
        return simpleResponse;
    }

    @Override
    public FeedbackResponce update(Long id, FeedbackRequest feedbackRequest) {
        Feedback feedback1 = feedbackRepository.findById(id).get();
        feedback1.setFeedback(feedbackRequest.getFeedback());
        feedback1.setMedia(feedbackRequest.getMedia());
        feedback1.setCreatedAt(LocalDateTime.now());
        feedback1.setProductEvaluation(feedbackRequest.getProductEvaluation());
        feedbackRepository.save(feedback1);
        return mapToResponse(feedback1);

    }

    @Override
    public FeedbackResponce getById(Long id) {
        Feedback feedback = feedbackRepository.findById(id).get();
        return mapToResponse(feedback);
    }

    @Override
    public SimpleResponse delete(Long id) {
        SimpleResponse simpleResponse = new SimpleResponse();
        Feedback feedback = new Feedback();
        boolean exists = feedbackRepository.existsById(id);
        if (exists) {
            feedback = feedbackRepository.findById(id).get();
        }
        if (Objects.equals(feedback.getId(), id)) {
            feedbackRepository.delete(feedback);
            simpleResponse.setHttpStatus(HttpStatus.OK);
            simpleResponse.setMessage("Ваш отзыв успешно удален");
        } else {
            simpleResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            simpleResponse.setMessage("Такого отзыва нет");
        }
        return simpleResponse;
    }

    @Override
    public FeedbackResponce reply(Long id, FeedbackRequest feedbackRequest) {
        Feedback feedback2 = feedbackRepository.findById(id).get();
        feedback2.setAdminReplay(feedbackRequest.getAdminReplay());
        feedbackRepository.save(feedback2);
        return mapToResponse(feedback2);
    }

    public FeedbackResponce mapToResponse(Feedback feedback) {
        FeedbackResponce feedbackResponce = new FeedbackResponce();
        feedbackResponce.setId(feedback.getId());
        feedbackResponce.setFeedback(feedback.getFeedback());
        feedbackResponce.setProductEvaluation(feedback.getProductEvaluation());
        feedbackResponce.setMedia(feedback.getMedia());
        feedbackResponce.setAdminReplay(feedback.getAdminReplay());
        feedbackResponce.setProduct(feedback.getProduct().getName());
        feedbackResponce.setFirstName(feedback.getUser().getFirstName());
        feedbackResponce.setLastName(feedback.getUser().getLastName());
        feedbackResponce.setCreatedDate(feedback.getCreatedAt());
        return feedbackResponce;
    }

    private Feedback mapToEntity(FeedbackRequest feedbackRequest) {
        Feedback feedback = new Feedback();
        feedback.setFeedback(feedbackRequest.getFeedback());
        feedback.setMedia(feedbackRequest.getMedia());
        feedback.setProductEvaluation(feedbackRequest.getProductEvaluation());
        feedback.setProduct(productRepository.getById(feedbackRequest.getProduct()));
        feedback.setUser(userRepository.getById(feedbackRequest.getUser()));
        feedback.setCreatedAt(LocalDateTime.now());
        return feedback;
    }

    public RatingResponce rating(Long id) {
        RatingResponce ratingResponce = new RatingResponce();
        ratingResponce.setOne(feedbackRepository.countRating(id, (byte) 1));
        ratingResponce.setTwo(feedbackRepository.countRating(id, (byte) 2));
        ratingResponce.setThree(feedbackRepository.countRating(id, (byte) 3));
        ratingResponce.setFour(feedbackRepository.countRating(id, (byte) 4));
        ratingResponce.setFive(feedbackRepository.countRating(id, (byte) 5));
        ratingResponce.setRating(feedbackRepository.rating(id));
        ratingResponce.setAllFeedback(feedbackRepository.allfeedback(id));
        return ratingResponce;
    }

    @Override
    public List<FeedbackResponce> getAllFeedbacksByProductId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Feedback> feedbacks = feedbackRepository.findFeedbacksByProductId(id, pageable);
        List<FeedbackResponce> feedbackResponceList = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            feedbackResponceList.add(mapToResponse(feedback));
        }
        return feedbackResponceList;
    }
}