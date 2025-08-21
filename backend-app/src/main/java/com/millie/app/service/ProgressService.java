package com.millie.app.service;

import com.millie.app.dto.ReadingProgressResponse;
import com.millie.app.dto.ViewerCloseRequest;
import com.millie.app.entity.Book;
import com.millie.app.entity.ReadingProgress;
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
        
        // Create new reading progress entry
        ReadingProgress progress = new ReadingProgress();
        progress.setUserId(DEFAULT_USER_ID);
        progress.setBookId(request.getBookId());
        progress.setLastPage(request.getLastPage());
        progress.setLastOpenedAt(OffsetDateTime.now());
        
        // Save progress
        ReadingProgress savedProgress = readingProgressRepository.save(progress);
        
        return convertToResponse(savedProgress);
    }
    
    public ReadingProgressResponse getProgress(Long userId, Long bookId) {
        Optional<ReadingProgress> progress = readingProgressRepository
                .findLatestByUserIdAndBookId(userId, bookId);
        
        if (progress.isEmpty()) {
            return null;
        }
        
        return convertToResponse(progress.get());
    }
    
    private ReadingProgressResponse convertToResponse(ReadingProgress progress) {
        return new ReadingProgressResponse(
                progress.getUserId(),
                progress.getBookId(),
                progress.getLastPage(),
                progress.getLastOpenedAt(),
                progress.getCreatedAt(),
                progress.getUpdatedAt(),
                null, // lastSummaryText는 summaries 테이블로 이동
                null  // lastSummaryLengthOpt는 summaries 테이블로 이동
        );
    }
}
