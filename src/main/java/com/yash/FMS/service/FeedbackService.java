package com.yash.FMS.service;
import com.yash.FMS.dto.FeedbackDTO;
import java.util.List;
import java.util.Optional;

/**
 * FeedbackService: Defines operations for managing Feedback entities.
 */
public interface FeedbackService {
    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);
    List<FeedbackDTO> getAllFeedbacks();
    Optional<FeedbackDTO> getFeedbackById(Long id);
    FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO);
    void deleteFeedback(Long id);
}