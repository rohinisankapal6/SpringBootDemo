package com.yash.FMS.controller;

import com.yash.FMS.dto.FeedbackDTO;
import com.yash.FMS.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * FeedbackController: REST API endpoints for managing Feedback entities.
 */
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * Creates a new Feedback.
     * @param feedbackDTO The Feedback data.
     * @return ResponseEntity containing the created Feedback and HTTP status.
     */
    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO createdFeedback = feedbackService.createFeedback(feedbackDTO);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Feedbacks.
     * @return ResponseEntity containing a list of FeedbackDTOs.
     */
    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    /**
     * Retrieves a Feedback by ID.
     * @param id The ID of the Feedback to retrieve.
     * @return ResponseEntity containing the FeedbackDTO if found, or NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        Optional<FeedbackDTO> feedback = feedbackService.getFeedbackById(id);
        return feedback.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates a Feedback by ID.
     * @param id The ID of the Feedback to update.
     * @param feedbackDTO The updated Feedback data.
     * @return ResponseEntity containing the updated FeedbackDTO if successful, or NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO updatedFeedback = feedbackService.updateFeedback(id, feedbackDTO);
        if (updatedFeedback != null) {
            return ResponseEntity.ok(updatedFeedback);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a Feedback by ID.
     * @param id The ID of the Feedback to delete.
     * @return ResponseEntity with NO_CONTENT status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}