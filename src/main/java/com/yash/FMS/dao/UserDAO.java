package com.yash.FMS.dao;

import com.yash.FMS.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDAO: CRUD operations for User entities.
 */
@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    /**
     * Finds a User by username.
     * @param username The username to search for.
     * @return The User, or null if not found.
     */
    User findByUsername(String username);
}