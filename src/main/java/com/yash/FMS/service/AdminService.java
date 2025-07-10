package com.yash.FMS.service;

import com.yash.FMS.dto.AdminDTO;

import java.util.List;
import java.util.Optional;

/**
 * AdminService: Defines operations for managing Admin entities.
 */
public interface AdminService {
    AdminDTO createAdmin(AdminDTO adminDTO);
    List<AdminDTO> getAllAdmins();
    Optional<AdminDTO> getAdminById(Long id);
    AdminDTO updateAdmin(Long id, AdminDTO adminDTO);
    void deleteAdmin(Long id);
}