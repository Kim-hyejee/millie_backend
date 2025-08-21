package com.millie.app.repository;

import com.millie.app.entity.ReadingProgress;
import com.millie.app.entity.ReadingProgressId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, ReadingProgressId> {
    
    @Query("SELECT rp FROM ReadingProgress rp WHERE rp.id.userId = :userId AND rp.id.bookId = :bookId")
    Optional<ReadingProgress> findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
