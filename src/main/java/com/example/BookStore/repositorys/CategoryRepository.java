package com.example.BookStore.repositorys;

import com.example.BookStore.mod.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("Select Kategory from Category Kategory join Kategory.books bookz where bookz.id = ?1 ")
    Category findByBookId(Long id);

    @Query("Select Kategory from Category Kategory join Kategory.books bookz where bookz.name like %?1%")
    Iterable<Category> findByName(String name);



}
