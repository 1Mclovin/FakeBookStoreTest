package com.example.BookStore.services;

import com.example.BookStore.mod.Category;
import com.example.BookStore.repositorys.BookRepository;
import com.example.BookStore.repositorys.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<Category> createCategory(Category category) {
        Category newCategory = categoryRepository.save(category);

        URI newCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(newCategoryUri).body(newCategory);
    }


    public ResponseEntity<?> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }


    public ResponseEntity<?> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category.get());
    }

    public ResponseEntity<?> updateCategory(Category category,Long id) {
        Optional<Category> findByCategoryId = categoryRepository.findById(id);
        if (findByCategoryId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        category.setId(findByCategoryId.get().getId());
        categoryRepository.save(category);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        deleteCategoryWithBooks(category.get());
        return ResponseEntity.accepted().build();
    }

    @Transactional
    public void deleteCategoryWithBooks(Category category) {
        bookRepository.deleteByCategoryId(category.getId());
        categoryRepository.delete(category);
    }

    public ResponseEntity<?> getCategoryByBookId(Long bookId) {
        return ResponseEntity.ok(categoryRepository.findByBookId(bookId));
    }

    public ResponseEntity<?> getBookByKeyword(String name) {
        return ResponseEntity.ok(categoryRepository.findByName(name));
    }



}
