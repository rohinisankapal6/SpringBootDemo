package com.yash.FMS.dao;

import com.yash.FMS.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FeedbackDAO: CRUD operations for Feedback entities.
 */
@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, Long> {
}