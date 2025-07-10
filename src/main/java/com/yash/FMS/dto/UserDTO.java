package com.yash.FMS.dto;

import lombok.Data;

/**
 * UserDTO: Data Transfer Object for User data.
 */
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
}