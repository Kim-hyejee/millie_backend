package com.millie.app.controller;

import com.millie.app.dto.SummaryFeedbackRequest;
import com.millie.app.dto.SummaryFeedbackResponse;
import com.millie.app.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summary-feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    
    private final FeedbackService feedbackService;
    
    @PostMapping
    public ResponseEntity<SummaryFeedbackResponse> createFeedback(@RequestBody SummaryFeedbackRequest request) {
        try {
            SummaryFeedbackResponse response = feedbackService.createFeedback(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("already exists")) {
                return ResponseEntity.status(409).build();
            }
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<SummaryFeedbackResponse> getMyFeedback(@RequestParam Long bookId) {
        SummaryFeedbackResponse feedback = feedbackService.getMyFeedback(bookId);
        
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(feedback);
    }
}
