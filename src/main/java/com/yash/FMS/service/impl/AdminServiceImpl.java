package com.yash.FMS.service.impl;

import com.yash.FMS.dao.AdminDAO;
import com.yash.FMS.dto.AdminDTO;
import com.yash.FMS.domain.Admin;
import com.yash.FMS.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AdminServiceImpl: Manages Admin entities.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new Admin (hashes password).
     * @param adminDTO The Admin data.
     * @return The created AdminDTO.
     */
    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = convertToEntity(adminDTO);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin savedAdmin = adminDAO.save(admin);
        return convertToDTO(savedAdmin);
    }

    /**
     * Retrieves all Admins.
     * @return A list of AdminDTOs.
     */
    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an Admin by ID.
     * @param id The ID to search for.
     * @return An Optional containing the AdminDTO.
     */
    @Override
    public Optional<AdminDTO> getAdminById(Long id) {
        return adminDAO.findById(id).map(this::convertToDTO);
    }

    /**
     * Updates an Admin (hashes password if provided).
     * @param id The ID of the Admin to update.
     * @param adminDTO The updated Admin data.
     * @return The updated AdminDTO, or null if not found.
     */
    @Override
    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        return adminDAO.findById(id)
                .map(existingAdmin -> {
                    Admin admin = convertToEntity(adminDTO);
                    admin.setId(id);
                    if (adminDTO.getPassword() != null && !adminDTO.getPassword().isEmpty()) {
                        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
                    } else {
                        admin.setPassword(existingAdmin.getPassword());
                    }
                    Admin updatedAdmin = adminDAO.save(admin);
                    return convertToDTO(updatedAdmin);
                })
                .orElse(null);
    }

    /**
     * Deletes an Admin by ID.
     * @param id The ID to delete.
     */
    @Override
    public void deleteAdmin(Long id) {
        adminDAO.deleteById(id);
    }

    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setUsername(admin.getUsername());
        return adminDTO; // Excludes password
    }

    private Admin convertToEntity(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        return admin;
    }
}