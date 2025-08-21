package com.millie.app.repository;

import com.millie.app.entity.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {
    
    @Query("SELECT rp FROM ReadingProgress rp WHERE rp.userId = :userId AND rp.bookId = :bookId ORDER BY rp.lastPage DESC")
    List<ReadingProgress> findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
    
    @Query("SELECT rp FROM ReadingProgress rp WHERE rp.userId = :userId AND rp.bookId = :bookId ORDER BY rp.lastPage DESC LIMIT 1")
    Optional<ReadingProgress> findLatestByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
