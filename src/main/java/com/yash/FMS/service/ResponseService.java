package com.yash.FMS.service;
import com.yash.FMS.dto.ResponseDTO;
import java.util.List;
import java.util.Optional;

/**
 * ResponseService: Defines operations for managing Response entities.
 */
public interface ResponseService {
    List<ResponseDTO> getAllResponses();
    Optional<ResponseDTO> getResponseById(Long id);
    ResponseDTO createResponse(ResponseDTO responseDTO);
    ResponseDTO updateResponse(Long id, ResponseDTO responseDTO);
    void deleteResponse(Long id);
}