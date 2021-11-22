package com.example.BookStore.repositorys;

import com.example.BookStore.mod.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select bookz from Book bookz where bookz.category.id = ?1")
    List<Book> findByCategoryId(Long id);

    @Query("select bookz from Book bookz where upper(bookz.category.name) like upper(concat('%',?1,'%'))")
    List<Book> findByCategoryNameContainsIgnoreCase(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book bookz WHERE bookz.category.id = ?1")
    void deleteByCategoryId(long id);


}
