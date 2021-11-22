package com.example.BookStore.services;

import com.example.BookStore.mod.Book;
import com.example.BookStore.mod.Category;
import com.example.BookStore.repositorys.BookRepository;
import com.example.BookStore.repositorys.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    // methods for post a book., get book by id.,getting all books. , update a book., delete a book., get a book by category id, get a book by category name
    public ResponseEntity<?> createBook(Book book) {
        Optional<Category> categories = categoryRepository.findById(book.getCategory().getId());

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        book.setCategory(categories.get());
        Book newBook = bookRepository.save(book);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBook.getId()).toUri();
        return ResponseEntity.created(location).body(newBook);
    }


    public ResponseEntity<?> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (bookRepository.findById(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(book.get());
    }

    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }


    public ResponseEntity<?> updateBook(Book book, Long id) {
        Optional<Category> categories = categoryRepository
                .findById(book.getCategory().getId());
        Optional<Book> books = bookRepository.findById(id);

        if (categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (bookRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        book.setCategory(categories.get());
        book.setId(books.get().getId());
        bookRepository.save(book);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        bookRepository.delete(book.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?>getBookByCategoryId(Long id) {
        return ResponseEntity.ok(bookRepository.findByCategoryId(id));
    }

    public ResponseEntity<?>getBookByCategoryName(String name) {
        return ResponseEntity.ok(bookRepository.findByCategoryNameContainsIgnoreCase(name));
    }









}
