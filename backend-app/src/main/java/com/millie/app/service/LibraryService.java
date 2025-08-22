package com.millie.app.service;

import com.millie.app.dto.BookResponse;
import com.millie.app.entity.Book;
import com.millie.app.entity.ReadingProgress;
import com.millie.app.repository.BookRepository;
import com.millie.app.repository.ReadingProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final ReadingProgressRepository readingProgressRepository;
    private final BookRepository bookRepository;

    /**
     * 내 서재에 책 추가 (멱등성 보장)
     * reading_progress에 (user_id, book_id) 행이 없으면 생성(last_page=0), 있으면 그대로 두기
     */
    @Transactional
    public void addToLibrary(Long userId, Long bookId) {
        // 책이 존재하는지 확인
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        // 이미 서재에 있는지 확인 (더 효율적인 쿼리 사용)
        boolean exists = readingProgressRepository.existsByUserIdAndBookId(userId, bookId);
        
        if (!exists) {
            // 서재에 없으면 새로 추가 (last_page=0)
            ReadingProgress newProgress = new ReadingProgress();
            newProgress.setUserId(userId);
            newProgress.setBookId(bookId);
            newProgress.setLastPage(0);
            newProgress.setLastOpenedAt(OffsetDateTime.now());
            newProgress.setCreatedAt(OffsetDateTime.now());
            newProgress.setUpdatedAt(OffsetDateTime.now());

            readingProgressRepository.save(newProgress);
        }
        // 이미 있으면 아무것도 하지 않음 (멱등성)
    }

    /**
     * 내 서재에서 책 제거
     * reading_progress 행을 삭제
     */
    @Transactional
    public void removeFromLibrary(Long userId, Long bookId) {
        // 해당 사용자의 해당 책에 대한 reading_progress 삭제
        readingProgressRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    /**
     * 내 서재 목록 조회 (최적화된 쿼리)
     * reading_progress를 books와 조인해서 해당 유저의 책 리스트 생성
     */
    public List<BookResponse> getMyLibrary(Long userId) {
        // 사용자의 reading_progress만 조회 (더 효율적)
        List<ReadingProgress> myProgress = readingProgressRepository.findByUserId(userId);

        return myProgress.stream()
                .map(progress -> {
                    Book book = bookRepository.findById(progress.getBookId())
                            .orElse(null);

                    if (book != null) {
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
                    return null;
                })
                .filter(bookResponse -> bookResponse != null)
                .collect(Collectors.toList());
    }

    /**
     * 특정 책이 내 서재에 있는지 확인
     */
    public boolean isInLibrary(Long userId, Long bookId) {
        return readingProgressRepository.existsByUserIdAndBookId(userId, bookId);
    }

    /**
     * 내 서재에 있는 책의 수 조회
     */
    public long getMyLibraryCount(Long userId) {
        return readingProgressRepository.countByUserId(userId);
    }
}
