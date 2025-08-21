package com.millie.app.entity;

import com.millie.app.enums.LengthOption;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "reading_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgress {
    
    @EmbeddedId
    private ReadingProgressId id;
    
    @Column(name = "last_page", nullable = false)
    private Integer lastPage;
    
    @Column(name = "last_opened_at", nullable = false)
    private OffsetDateTime lastOpenedAt;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
    
    @Column(name = "last_summary_text", columnDefinition = "TEXT")
    private String lastSummaryText;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "last_summary_length_opt", length = 20)
    private LengthOption lastSummaryLengthOpt;
}
