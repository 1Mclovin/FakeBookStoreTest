package com.example.BookStore.controllers;

import com.example.BookStore.mod.Category;
import com.example.BookStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/cats")
    public ResponseEntity<?> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.updateCategory(category, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @PostMapping("/cats")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }


    @GetMapping("/book/{categoryId}")
    public ResponseEntity<?> getCategoryByBookId(@PathVariable Long categoryId) {
        return categoryService.getCategoryByBookId(categoryId);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getCategoryByName(@RequestParam(value = "name", required = false) String name) {
        return categoryService.getBookByKeyword(name);
    }







}
