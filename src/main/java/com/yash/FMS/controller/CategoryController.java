package com.yash.FMS.controller;

import com.yash.FMS.dto.CategoryDTO;
import com.yash.FMS.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *  CategoryController: REST API endpoints for managing Category entities.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieves all Categories.
     * @return ResponseEntity containing a list of CategoryDTOs.
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * Retrieves a Category by ID.
     * @param id The ID of the Category to retrieve.
     * @return ResponseEntity containing the CategoryDTO if found, or NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Optional<CategoryDTO> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new Category.
     * @param categoryDTO The Category data.
     * @return ResponseEntity containing the created Category and HTTP status.
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    /**
     * Updates a Category by ID.
     * @param id The ID of the Category to update.
     * @param categoryDTO The updated Category data.
     * @return ResponseEntity containing the updated CategoryDTO if successful, or NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a Category by ID.
     * @param id The ID of the Category to delete.
     * @return ResponseEntity with NO_CONTENT status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}