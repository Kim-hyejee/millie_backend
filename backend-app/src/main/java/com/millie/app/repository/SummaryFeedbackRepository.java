package com.millie.app.repository;

import com.millie.app.entity.SummaryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SummaryFeedbackRepository extends JpaRepository<SummaryFeedback, Long> {
    
    @Query("SELECT sf FROM SummaryFeedback sf WHERE sf.user.id = :userId AND sf.bookId = :bookId")
    Optional<SummaryFeedback> findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
