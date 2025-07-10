package com.yash.FMS.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * Response: Entity representing a response to feedback.
 */
@Entity
@Table(name = "responses")
@Data
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private String responseText;

    @Column(name = "response_date")
    private Date responseDate;
}