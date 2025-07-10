package com.yash.FMS.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Category: Entity representing a feedback category.
 */
@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String description;
}