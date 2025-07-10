package com.yash.FMS.service;
import com.yash.FMS.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

/**
 * CategoryService: Defines operations for managing Category entities.
 */
public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    Optional<CategoryDTO> getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}