package com.yash.FMS.service.impl;

import com.yash.FMS.dao.UserDAO;
import com.yash.FMS.dto.UserDTO;
import com.yash.FMS.domain.User;
import com.yash.FMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserServiceImpl: Manages User entities.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new User (hashes password).
     * @param userDTO The User data.
     * @return The created UserDTO.
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
        User savedUser = userDAO.save(user);

        return convertToDTO(savedUser);
    }

    /**
     * Retrieves a User by ID.
     * @param id The ID to search for.
     * @return An Optional containing the UserDTO.
     */
    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userDAO.findById(id).map(this::convertToDTO);
    }

    /**
     * Retrieves all Users.
     * @return A list of UserDTOs.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates a User (hashes password if provided).
     * @param id The ID of the User to update.
     * @param userDTO The updated User data.
     * @return The updated UserDTO, or null if not found.
     */
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userDAO.findById(id)
                .map(existingUser -> {
                    User user = convertToEntity(userDTO);
                    user.setId(id);  //Ensure the ID is set.
                    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    } else {
                        user.setPassword(existingUser.getPassword()); //Keep existing password if not provided
                    }
                    User updatedUser = userDAO.save(user);
                    return convertToDTO(updatedUser);
                })
                .orElse(null);
    }

    /**
     * Deletes a User by ID.
     * @param id The ID to delete.
     */
    @Override
    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }

    /**
     * Converts a User entity to a UserDTO.
     * @param user The User to convert.
     * @return The converted UserDTO.
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    /**
     * Converts a UserDTO to a User entity.
     * @param userDTO The UserDTO to convert.
     * @return The converted User entity.
     */
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());  //The password will be encoded by the calling methods
        return user;
    }
}











































//package com.yash.FMS.service.impl;
//
//import com.yash.FMS.dao.UserDAO;
//import com.yash.FMS.dto.UserDTO;
//import com.yash.FMS.domain.User;
//import com.yash.FMS.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserDAO userDAO;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDTO createUser(UserDTO userDTO) {
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash the password
//        User savedUser = userDAO.save(user);
//
//        UserDTO savedUserDTO = convertToDTO(savedUser);
//
//        return savedUserDTO;
//    }
//
//    @Override
//    public Optional<UserDTO> getUserById(Long id) {
//        return userDAO.findById(id).map(this::convertToDTO);
//    }
//
//    @Override
//    public List<UserDTO> getAllUsers() {
//        return userDAO.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDTO updateUser(Long id, UserDTO userDTO) {
//        return userDAO.findById(id)
//                .map(existingUser -> {
//                    existingUser.setUsername(userDTO.getUsername());
//                    existingUser.setEmail(userDTO.getEmail());
//                    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
//                        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//                    }
//                    User updatedUser = userDAO.save(existingUser);
//                    return convertToDTO(updatedUser);
//                })
//                .orElse(null);
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userDAO.deleteById(id);
//    }
//
//    private UserDTO convertToDTO(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setUsername(user.getUsername());
//        userDTO.setEmail(user.getEmail());
//        return userDTO;
//    }
//
//
//}