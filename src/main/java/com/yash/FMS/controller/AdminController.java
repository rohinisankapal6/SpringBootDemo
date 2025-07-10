package com.yash.FMS.controller;

import com.yash.FMS.dto.AdminDTO;
import com.yash.FMS.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *  AdminController: REST API endpoints for managing Admin entities.
 */
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Creates a new Admin.
     * @param adminDTO The Admin data.
     * @return ResponseEntity containing the created Admin and HTTP status.
     */
    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO createdAdmin = adminService.createAdmin(adminDTO);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Admins.
     * @return ResponseEntity containing a list of AdminDTOs.
     */
    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    /**
     * Retrieves an Admin by ID.
     * @param id The ID of the Admin to retrieve.
     * @return ResponseEntity containing the AdminDTO if found, or NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        Optional<AdminDTO> admin = adminService.getAdminById(id);
        return admin.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates an Admin by ID.
     * @param id The ID of the Admin to update.
     * @param adminDTO The updated Admin data.
     * @return ResponseEntity containing the updated AdminDTO if successful, or NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmin(id, adminDTO);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an Admin by ID.
     * @param id The ID of the Admin to delete.
     * @return ResponseEntity with NO_CONTENT status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Authenticates an Admin.
     * @param loginDto The AdminDTO containing the username and password.
     * @return ResponseEntity indicating success or failure.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@RequestBody AdminDTO loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("Admin signed-in successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Admin Authentication failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}