package us.peaksoft.gadgetarium.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.FeedbackRequest;
import us.peaksoft.gadgetarium.dto.FeedbackResponce;
import us.peaksoft.gadgetarium.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("api/public/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping
    public List<FeedbackResponce> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping
    public FeedbackResponce save(@RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.save(feedbackRequest);
    }
}