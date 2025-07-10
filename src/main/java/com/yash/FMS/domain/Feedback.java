package com.yash.FMS.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * Feedback: Entity representing user feedback.
 */
@Entity
@Table(name = "feedbacks")
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "submission_date")
    private Date submissionDate;

    @Column(name = "status")
    private String status; // e.g., "Open", "In Progress", "Closed"
}