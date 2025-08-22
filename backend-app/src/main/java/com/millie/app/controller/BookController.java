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

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn) {
        
        List<Book> books;
        
        if (title != null && !title.trim().isEmpty()) {
            books = bookRepository.findByTitleContainingIgnoreCase(title.trim());
        } else if (author != null && !author.trim().isEmpty()) {
            books = bookRepository.findByAuthorContainingIgnoreCase(author.trim());
        } else if (isbn != null && !isbn.trim().isEmpty()) {
            books = bookRepository.findByIsbnContainingIgnoreCase(isbn.trim());
        } else {
            // 검색어가 없으면 전체 목록 반환
            books = bookRepository.findAll();
        }
        
        List<BookResponse> responses = books.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search/keyword")
    public ResponseEntity<List<BookResponse>> searchBooksByKeyword(
            @RequestParam String keyword) {
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<Book> books = bookRepository.findByKeywordContainingIgnoreCase(keyword.trim());
        
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
        // 임시로 userId=1 사용
        ReadingProgressResponse progress = progressService.getProgress(1L, bookId);

        if (progress == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(progress);
    }

    @GetMapping("/{bookId}/summary/latest")
    public ResponseEntity<String> getLatestSummary(@PathVariable Long bookId) {
        // 임시로 userId=1 사용
        ReadingProgressResponse progress = progressService.getProgress(1L, bookId);

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
                book.getImageUrl(),
                book.getCreatedAt()
        );
    }
}
