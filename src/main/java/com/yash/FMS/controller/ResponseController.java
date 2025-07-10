package com.yash.FMS.controller;

import com.yash.FMS.dto.ResponseDTO;
import com.yash.FMS.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 *  ResponseController: REST API endpoints for managing Response entities.
 */
@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    /**
     * Retrieves all Responses.
     * @return ResponseEntity containing a list of ResponseDTOs.
     */
    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllResponses() {
        return ResponseEntity.ok(responseService.getAllResponses());
    }

    /**
     * Retrieves a Response by ID.
     * @param id The ID of the Response to retrieve.
     * @return ResponseEntity containing the ResponseDTO if found, or NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getResponseById(@PathVariable Long id) {
        Optional<ResponseDTO> response = responseService.getResponseById(id);
        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new Response.
     * @param responseDTO The Response data.
     * @return ResponseEntity containing the created Response and HTTP status.
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> createResponse(@RequestBody ResponseDTO responseDTO) {
        ResponseDTO createdResponse = responseService.createResponse(responseDTO);
        return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
    }

    /**
     * Updates a Response by ID.
     * @param id The ID of the Response to update.
     * @param responseDTO The updated Response data.
     * @return ResponseEntity containing the updated ResponseDTO if successful, or NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateResponse(@PathVariable Long id, @RequestBody ResponseDTO responseDTO) {
        ResponseDTO updatedResponse = responseService.updateResponse(id, responseDTO);
        if (updatedResponse != null) {
            return ResponseEntity.ok(updatedResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a Response by ID.
     * @param id The ID of the Response to delete.
     * @return ResponseEntity with NO_CONTENT status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        responseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}