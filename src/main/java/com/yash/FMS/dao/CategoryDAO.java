package com.yash.FMS.dao;

import com.yash.FMS.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryDAO: CRUD operations for Category entities.
 */
@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {
}