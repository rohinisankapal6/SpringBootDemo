package com.yash.FMS.dto;

import lombok.Data;

import java.util.Date;

/**
 * FeedbackDTO: Data Transfer Object for Feedback data.
 */
@Data
public class FeedbackDTO {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String description;
    private Date submissionDate;
    private String status;
}