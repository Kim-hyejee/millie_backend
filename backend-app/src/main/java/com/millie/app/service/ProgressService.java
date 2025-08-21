package com.millie.app.service;

import com.millie.app.dto.ReadingProgressResponse;
import com.millie.app.dto.ViewerCloseRequest;
import com.millie.app.entity.Book;
import com.millie.app.entity.ReadingProgress;
import com.millie.app.entity.ReadingProgressId;
import com.millie.app.entity.User;
import com.millie.app.enums.LengthOption;
import com.millie.app.repository.BookRepository;
import com.millie.app.repository.ReadingProgressRepository;
import com.millie.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressService {
    
    private final ReadingProgressRepository readingProgressRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final DummyTextSource dummyTextSource;
    private final DummySummarizer dummySummarizer;
    
    private static final Long DEFAULT_USER_ID = 1L;
    
    @Transactional
    public ReadingProgressResponse closeViewerAndSummarize(ViewerCloseRequest request) {
        // Validate book exists
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        
        // Validate lastPage range
        if (request.getLastPage() < 1 || request.getLastPage() > book.getTotalPages()) {
            throw new RuntimeException("Invalid last page");
        }
        
        // Get or create reading progress
        ReadingProgressId id = new ReadingProgressId(DEFAULT_USER_ID, request.getBookId());
        Optional<ReadingProgress> existingProgress = readingProgressRepository.findById(id);
        
        ReadingProgress progress;
        if (existingProgress.isPresent()) {
            progress = existingProgress.get();
            // Only update if new lastPage is greater
            if (request.getLastPage() > progress.getLastPage()) {
                progress.setLastPage(request.getLastPage());
            }
        } else {
            progress = new ReadingProgress();
            progress.setId(id);
            progress.setLastPage(request.getLastPage());
        }
        
        // Update timestamps and summary
        progress.setLastOpenedAt(OffsetDateTime.now());
        progress.setLastSummaryLengthOpt(request.getLengthOpt());
        
        // Generate summary
        String summaryText = dummyTextSource.getTextFromPages(request.getBookId(), 1, request.getLastPage());
        String summary = dummySummarizer.summarize(summaryText, request.getLengthOpt());
        progress.setLastSummaryText(summary);
        
        // Save progress
        ReadingProgress savedProgress = readingProgressRepository.save(progress);
        
        return convertToResponse(savedProgress);
    }
    
    public ReadingProgressResponse getProgress(Long userId, Long bookId) {
        Optional<ReadingProgress> progress = readingProgressRepository
                .findByUserIdAndBookId(userId, bookId);
        
        if (progress.isEmpty()) {
            return null;
        }
        
        return convertToResponse(progress.get());
    }
    
    private ReadingProgressResponse convertToResponse(ReadingProgress progress) {
        return new ReadingProgressResponse(
                progress.getId().getUserId(),
                progress.getId().getBookId(),
                progress.getLastPage(),
                progress.getLastOpenedAt(),
                progress.getCreatedAt(),
                progress.getUpdatedAt(),
                progress.getLastSummaryText(),
                progress.getLastSummaryLengthOpt()
        );
    }
}
