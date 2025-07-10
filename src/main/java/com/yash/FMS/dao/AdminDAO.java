package com.yash.FMS.dao;

import com.yash.FMS.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AdminDAO: CRUD operations for Admin entities.
 */
@Repository
public interface AdminDAO extends JpaRepository<Admin, Long> {

    /**
     * Finds an Admin by username.
     * @param username The username to search for.
     * @return The Admin, or null if not found.
     */
    Admin findByUsername(String username);
}