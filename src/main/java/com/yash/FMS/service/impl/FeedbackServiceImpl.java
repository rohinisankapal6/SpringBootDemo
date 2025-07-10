package com.yash.FMS.service.impl;

import com.yash.FMS.dao.FeedbackDAO;
import com.yash.FMS.dao.UserDAO;
import com.yash.FMS.dao.CategoryDAO;
import com.yash.FMS.dto.FeedbackDTO;
import com.yash.FMS.domain.Feedback;
import com.yash.FMS.domain.User;
import com.yash.FMS.domain.Category;
import com.yash.FMS.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FeedbackServiceImpl: Manages Feedback entities.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    /**
     * Creates a new Feedback.
     * @param feedbackDTO The Feedback data.
     * @return The created FeedbackDTO.
     */
    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = convertToEntity(feedbackDTO);
        Feedback savedFeedback = feedbackDAO.save(feedback);
        return convertToDTO(savedFeedback);
    }

    /**
     * Retrieves all Feedbacks.
     * @return A list of FeedbackDTOs.
     */
    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a Feedback by ID.
     * @param id The ID to search for.
     * @return An Optional containing the FeedbackDTO.
     */
    @Override
    public Optional<FeedbackDTO> getFeedbackById(Long id) {
        return feedbackDAO.findById(id).map(this::convertToDTO);
    }

    /**
     * Updates a Feedback.
     * @param id The ID of the Feedback to update.
     * @param feedbackDTO The updated Feedback data.
     * @return The updated FeedbackDTO, or null if not found.
     */
    @Override
    public FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        return feedbackDAO.findById(id)
                .map(existingFeedback -> {
                    Feedback feedback = convertToEntity(feedbackDTO);
                    feedback.setId(id);
                    Feedback updatedFeedback = feedbackDAO.save(feedback);
                    return convertToDTO(updatedFeedback);
                })
                .orElse(null);
    }

    /**
     * Deletes a Feedback by ID.
     * @param id The ID to delete.
     */
    @Override
    public void deleteFeedback(Long id) {
        feedbackDAO.deleteById(id);
    }

    private FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setUserId(feedback.getUser().getId());
        feedbackDTO.setCategoryId(feedback.getCategory().getId());
        feedbackDTO.setTitle(feedback.getTitle());
        feedbackDTO.setDescription(feedback.getDescription());
        feedbackDTO.setSubmissionDate(feedback.getSubmissionDate());
        feedbackDTO.setStatus(feedback.getStatus());
        return feedbackDTO;
    }

    private Feedback convertToEntity(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        User user = userDAO.findById(feedbackDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + feedbackDTO.getUserId()));
        Category category = categoryDAO.findById(feedbackDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + feedbackDTO.getCategoryId()));

        feedback.setUser(user);
        feedback.setCategory(category);
        feedback.setTitle(feedbackDTO.getTitle());
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setSubmissionDate(feedbackDTO.getSubmissionDate());
        feedback.setStatus(feedbackDTO.getStatus());
        return feedback;
    }
}