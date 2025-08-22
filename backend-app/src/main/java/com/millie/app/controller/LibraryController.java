package com.millie.app.controller;

import com.millie.app.dto.BookResponse;
import com.millie.app.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    // 내 서재에 책 추가
    @PostMapping("/add/{bookId}")
    public ResponseEntity<String> addToLibrary(@PathVariable Long bookId) {
        try {
            // 임시로 userId=1 사용
            libraryService.addToLibrary(1L, bookId);
            return ResponseEntity.ok("서재에 추가되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("서재 추가 실패: " + e.getMessage());
        }
    }

    // 내 서재에서 책 제거
    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<String> removeFromLibrary(@PathVariable Long bookId) {
        try {
            // 임시로 userId=1 사용
            libraryService.removeFromLibrary(1L, bookId);
            return ResponseEntity.ok("서재에서 제거되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("서재 제거 실패: " + e.getMessage());
        }
    }

    // 내 서재 목록 조회
    @GetMapping("/my-books")
    public ResponseEntity<List<BookResponse>> getMyLibrary() {
        try {
            // 임시로 userId=1 사용
            List<BookResponse> myBooks = libraryService.getMyLibrary(1L);
            return ResponseEntity.ok(myBooks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 특정 책이 내 서재에 있는지 확인
    @GetMapping("/check/{bookId}")
    public ResponseEntity<Boolean> isInLibrary(@PathVariable Long bookId) {
        try {
            // 임시로 userId=1 사용
            boolean isInLibrary = libraryService.isInLibrary(1L, bookId);
            return ResponseEntity.ok(isInLibrary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // 내 서재에 있는 책의 개수 조회
    @GetMapping("/count")
    public ResponseEntity<Long> getMyLibraryCount() {
        try {
            // 임시로 userId=1 사용
            long count = libraryService.getMyLibraryCount(1L);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(0L);
        }
    }
}
