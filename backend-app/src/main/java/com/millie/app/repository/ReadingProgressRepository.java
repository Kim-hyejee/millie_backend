package com.millie.app.repository;

import com.millie.app.entity.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {

    /**
     * 특정 사용자와 책의 reading progress 조회 (여러 개 있을 수 있음)
     */
    @Query("SELECT rp FROM ReadingProgress rp WHERE rp.userId = :userId AND rp.bookId = :bookId ORDER BY rp.lastPage DESC")
    List<ReadingProgress> findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

    /**
     * 특정 사용자와 책의 최신 reading progress 조회
     */
    @Query("SELECT rp FROM ReadingProgress rp WHERE rp.userId = :userId AND rp.bookId = :bookId ORDER BY rp.lastPage DESC")
    List<ReadingProgress> findLatestByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

    /**
     * 특정 사용자의 모든 reading progress 조회
     */
    @Query("SELECT rp FROM ReadingProgress rp WHERE rp.userId = :userId ORDER BY rp.updatedAt DESC")
    List<ReadingProgress> findByUserId(@Param("userId") Long userId);

    /**
     * 특정 사용자와 책의 reading progress 존재 여부 확인
     */
    @Query("SELECT COUNT(rp) > 0 FROM ReadingProgress rp WHERE rp.userId = :userId AND rp.bookId = :bookId")
    boolean existsByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

    /**
     * 특정 사용자의 reading progress 개수 조회
     */
    @Query("SELECT COUNT(rp) FROM ReadingProgress rp WHERE rp.userId = :userId")
    long countByUserId(@Param("userId") Long userId);

    /**
     * 특정 사용자와 책의 reading progress 삭제
     */
    @Modifying
    @Query("DELETE FROM ReadingProgress rp WHERE rp.userId = :userId AND rp.bookId = :bookId")
    void deleteByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
