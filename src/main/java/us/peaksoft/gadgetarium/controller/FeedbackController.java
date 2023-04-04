package us.peaksoft.gadgetarium.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.FeedbackRequest;
import us.peaksoft.gadgetarium.dto.FeedbackResponce;
import us.peaksoft.gadgetarium.dto.RatingResponce;
import us.peaksoft.gadgetarium.dto.SimpleResponse;
import us.peaksoft.gadgetarium.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("api/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping
    public List<FeedbackResponce> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping
    public SimpleResponse save(@RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.save(feedbackRequest);
    }

    @GetMapping("{id}")
    public FeedbackResponce getById(@PathVariable("id") Long id) {
        return feedbackService.getById(id);
    }

    @PutMapping("{id}")
    public FeedbackResponce update(@PathVariable("id") Long id, @RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.update(id, feedbackRequest);
    }

    @DeleteMapping("{id}")
    public SimpleResponse delete(@PathVariable("id") Long id) {
        return feedbackService.delete(id);
    }

    @PutMapping("/admin/{id}")
    public FeedbackResponce reply(@PathVariable("id") Long id, @RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.reply(id, feedbackRequest);
    }

    @GetMapping("/rating")
    public RatingResponce rating() {
        return feedbackService.rating();
    }
}