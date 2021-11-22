package com.example.BookStore.controllers;

import com.example.BookStore.mod.Book;
import com.example.BookStore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping ("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping ("/books/{id}")
    public ResponseEntity<?> updateBookById(@RequestBody Book book, @PathVariable Long id) {
        return bookService.updateBook(book, id);
    }

    @GetMapping("/books/search")
    public ResponseEntity<?> getBooksByCategoryName(@RequestParam(value = "name", required = false) String categoryName) {
        return bookService.getBookByCategoryName(categoryName);
    }


    @DeleteMapping ("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping ("/books/category/{bookId}")
    public ResponseEntity<?> getBooksByCategoryId(@PathVariable Long bookId) {
        return bookService.getBookByCategoryId(bookId);
    }







}
