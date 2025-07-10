package com.yash.FMS.dto;

import lombok.Data;

/**
 * AdminDTO (Data Transfer Object) representing Admin data.
 * Used for transferring Admin data between layers of the application.
 */
@Data
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
}