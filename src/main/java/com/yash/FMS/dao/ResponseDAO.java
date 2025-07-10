package com.yash.FMS.dao;

import com.yash.FMS.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ResponseDAO: CRUD operations for Response entities.
 */
@Repository
public interface ResponseDAO extends JpaRepository<Response, Long> {
}