package com.millie.app.dto;

import com.millie.app.enums.LengthOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgressResponse {
    private Long userId;
    private Long bookId;
    private Integer lastPage;
    private OffsetDateTime lastOpenedAt;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String lastSummaryText;
    private LengthOption lastSummaryLengthOpt;
}
