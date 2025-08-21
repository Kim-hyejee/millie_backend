package com.millie.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryFeedbackResponse {
    private Long id;
    private Long progressUserId;
    private Long bookId;
    private Long userId;
    private Integer rating;
    private Boolean isHelpful;
    private String comment;
    private OffsetDateTime createdAt;
}
