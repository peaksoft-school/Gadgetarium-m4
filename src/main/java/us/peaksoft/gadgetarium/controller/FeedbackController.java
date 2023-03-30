package us.peaksoft.gadgetarium.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.FeedbackRequest;
import us.peaksoft.gadgetarium.dto.FeedbackResponce;
import us.peaksoft.gadgetarium.dto.RatingResponce;
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
    public String save(@RequestBody FeedbackRequest feedbackRequest) {
        feedbackService.save(feedbackRequest);
        return "Ваш отзыв был успешно отправлен!";
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
    public String delete(@PathVariable("id") Long id) {
        feedbackService.delete(id);
        return "Ваш отзыв был удален";
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