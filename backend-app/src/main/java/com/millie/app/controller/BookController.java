package com.millie.app.controller;

import com.millie.app.dto.BookResponse;
import com.millie.app.dto.ReadingProgressResponse;
import com.millie.app.entity.Book;
import com.millie.app.repository.BookRepository;
import com.millie.app.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    
    private final BookRepository bookRepository;
    private final ProgressService progressService;
    
    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responses = books.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        
        return ResponseEntity.ok(convertToResponse(book));
    }
    
    @GetMapping("/{bookId}/progress")
    public ResponseEntity<ReadingProgressResponse> getProgress(@PathVariable Long bookId) {
        ReadingProgressResponse progress = progressService.getProgress(1L, bookId); // 임시로 userId=1 사용
        
        if (progress == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(progress);
    }
    
    @GetMapping("/{bookId}/summary/latest")
    public ResponseEntity<String> getLatestSummary(@PathVariable Long bookId) {
        ReadingProgressResponse progress = progressService.getProgress(1L, bookId); // 임시로 userId=1 사용
        
        if (progress == null || progress.getLastSummaryText() == null) {
            return ResponseEntity.ok("");
        }
        
        return ResponseEntity.ok(progress.getLastSummaryText());
    }
    
    private BookResponse convertToResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getTotalPages(),
                book.getIsbn(),
                book.getCreatedAt()
        );
    }
}
