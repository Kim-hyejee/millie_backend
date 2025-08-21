package com.millie.app.dto;

import lombok.Data;

@Data
public class SummaryFeedbackRequest {
    private Long bookId;
    private Integer rating;
    private Boolean isHelpful;
    private String comment;
}
