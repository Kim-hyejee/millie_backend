package com.millie.app.service;

import com.millie.app.dto.SummaryFeedbackRequest;
import com.millie.app.dto.SummaryFeedbackResponse;
import com.millie.app.entity.Book;
import com.millie.app.entity.ReadingProgress;
import com.millie.app.entity.ReadingProgressId;
import com.millie.app.entity.SummaryFeedback;
import com.millie.app.entity.User;
import com.millie.app.repository.BookRepository;
import com.millie.app.repository.ReadingProgressRepository;
import com.millie.app.repository.SummaryFeedbackRepository;
import com.millie.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    
    private final SummaryFeedbackRepository summaryFeedbackRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReadingProgressRepository readingProgressRepository;
    
    private static final Long DEFAULT_USER_ID = 1L;
    
    @Transactional
    public SummaryFeedbackResponse createFeedback(SummaryFeedbackRequest request) {
        // Validate book exists
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        
        // Validate rating range
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }
        
        // Check if feedback already exists
        Optional<SummaryFeedback> existingFeedback = summaryFeedbackRepository
                .findByUserIdAndBookId(DEFAULT_USER_ID, request.getBookId());
        
        if (existingFeedback.isPresent()) {
            throw new RuntimeException("Feedback already exists for this book");
        }
        
        // Get user
        User user = userRepository.findById(DEFAULT_USER_ID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Get reading progress
        ReadingProgressId progressId = new ReadingProgressId(DEFAULT_USER_ID, request.getBookId());
        ReadingProgress progress = readingProgressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("Reading progress not found"));
        
        // Create feedback
        SummaryFeedback feedback = new SummaryFeedback();
        feedback.setProgressUserId(progress.getId().getUserId());
        feedback.setBookId(request.getBookId());
        feedback.setUser(user);
        feedback.setRating(request.getRating());
        feedback.setIsHelpful(request.getIsHelpful());
        feedback.setComment(request.getComment());
        
        SummaryFeedback savedFeedback = summaryFeedbackRepository.save(feedback);
        
        return convertToResponse(savedFeedback);
    }
    
    public SummaryFeedbackResponse getMyFeedback(Long bookId) {
        Optional<SummaryFeedback> feedback = summaryFeedbackRepository
                .findByUserIdAndBookId(DEFAULT_USER_ID, bookId);
        
        if (feedback.isEmpty()) {
            return null;
        }
        
        return convertToResponse(feedback.get());
    }
    
    private SummaryFeedbackResponse convertToResponse(SummaryFeedback feedback) {
        return new SummaryFeedbackResponse(
                feedback.getId(),
                feedback.getProgressUserId(),
                feedback.getBookId(),
                feedback.getUser().getId(),
                feedback.getRating(),
                feedback.getIsHelpful(),
                feedback.getComment(),
                feedback.getCreatedAt()
        );
    }
}
