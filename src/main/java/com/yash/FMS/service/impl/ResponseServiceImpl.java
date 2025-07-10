package com.yash.FMS.service.impl;

import com.yash.FMS.dao.ResponseDAO;
import com.yash.FMS.dao.FeedbackDAO;
import com.yash.FMS.dao.AdminDAO;
import com.yash.FMS.dto.ResponseDTO;
import com.yash.FMS.domain.Response;
import com.yash.FMS.domain.Feedback;
import com.yash.FMS.domain.Admin;
import com.yash.FMS.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ResponseServiceImpl: Manages Response entities.
 */
@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseDAO responseDAO;

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private AdminDAO adminDAO;

    /**
     * Creates a new Response.
     * @param responseDTO The Response data.
     * @return The created ResponseDTO.
     */
    @Override
    public ResponseDTO createResponse(ResponseDTO responseDTO) {
        Response response = convertToEntity(responseDTO);
        Response savedResponse = responseDAO.save(response);
        return convertToDTO(savedResponse);
    }

    /**
     * Retrieves all Responses.
     * @return A list of ResponseDTOs.
     */
    @Override
    public List<ResponseDTO> getAllResponses() {
        return responseDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a Response by ID.
     * @param id The ID to search for.
     * @return An Optional containing the ResponseDTO.
     */
    @Override
    public Optional<ResponseDTO> getResponseById(Long id) {
        return responseDAO.findById(id).map(this::convertToDTO);
    }

    /**
     * Updates a Response.
     * @param id The ID of the Response to update.
     * @param responseDTO The updated Response data.
     * @return The updated ResponseDTO, or null if not found.
     */
    @Override
    public ResponseDTO updateResponse(Long id, ResponseDTO responseDTO) {
        return responseDAO.findById(id)
                .map(existingResponse -> {
                    Response response = convertToEntity(responseDTO);
                    response.setId(id);
                    Response updatedResponse = responseDAO.save(response);
                    return convertToDTO(updatedResponse);
                })
                .orElse(null);
    }

    /**
     * Deletes a Response by ID.
     * @param id The ID to delete.
     */
    @Override
    public void deleteResponse(Long id) {
        responseDAO.deleteById(id);
    }

    private ResponseDTO convertToDTO(Response response) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(response.getId());
        responseDTO.setFeedbackId(response.getFeedback().getId());
        responseDTO.setAdminId(response.getAdmin().getId());
        responseDTO.setResponseText(response.getResponseText());
        responseDTO.setResponseDate(response.getResponseDate());
        return responseDTO;
    }

    private Response convertToEntity(ResponseDTO responseDTO) {
        Response response = new Response();
        Feedback feedback = feedbackDAO.findById(responseDTO.getFeedbackId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid feedback ID: " + responseDTO.getFeedbackId()));
        Admin admin = adminDAO.findById(responseDTO.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + responseDTO.getAdminId()));

        response.setFeedback(feedback);
        response.setAdmin(admin);
        response.setResponseText(responseDTO.getResponseText());
        response.setResponseDate(responseDTO.getResponseDate());
        return response;
    }
}