package com.yash.FMS.dto;

import lombok.Data;

import java.util.Date;

/**
 * ResponseDTO: Data Transfer Object for Response data.
 */
@Data
public class ResponseDTO {
    private Long id;
    private Long feedbackId;
    private Long adminId;
    private String responseText;
    private Date responseDate;
}