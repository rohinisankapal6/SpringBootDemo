package com.yash.FMS.service.impl;

import com.yash.FMS.dao.CategoryDAO;
import com.yash.FMS.dto.CategoryDTO;
import com.yash.FMS.domain.Category;
import com.yash.FMS.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CategoryServiceImpl: Manages Category entities.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    /**
     * Creates a new Category.
     * @param categoryDTO The Category data.
     * @return The created CategoryDTO.
     */
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryDAO.save(category);
        return convertToDTO(savedCategory);
    }

    /**
     * Retrieves all Categories.
     * @return A list of CategoryDTOs.
     */
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a Category by ID.
     * @param id The ID to search for.
     * @return An Optional containing the CategoryDTO.
     */
    @Override
    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryDAO.findById(id).map(this::convertToDTO);
    }

    /**
     * Updates a Category.
     * @param id The ID of the Category to update.
     * @param categoryDTO The updated Category data.
     * @return The updated CategoryDTO, or null if not found.
     */
    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryDAO.findById(id)
                .map(existingCategory -> {
                    Category category = convertToEntity(categoryDTO);
                    category.setId(id);
                    Category updatedCategory = categoryDAO.save(category);
                    return convertToDTO(updatedCategory);
                })
                .orElse(null);
    }

    /**
     * Deletes a Category by ID.
     * @param id The ID to delete.
     */
    @Override
    public void deleteCategory(Long id) {
        categoryDAO.deleteById(id);
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}