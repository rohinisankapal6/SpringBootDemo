package com.yash.FMS.dto;

import lombok.Data;

/**
 * CategoryDTO: Data Transfer Object for Category data.
 */
@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
}