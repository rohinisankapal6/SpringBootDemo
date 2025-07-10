package com.yash.FMS.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Admin: Entity representing an administrator.
 */
@Entity
@Table(name = "admins")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}