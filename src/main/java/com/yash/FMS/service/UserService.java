package com.yash.FMS.service;

import com.yash.FMS.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * UserService: Defines operations for managing User entities.
 */
public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}