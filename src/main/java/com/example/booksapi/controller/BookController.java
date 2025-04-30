package com.example.booksapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.booksapi.model.Book;
import com.example.booksapi.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*") // Allow requests from React app
public class BookController {
    // new endpoint
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id the ID of the book to retrieve
     * @return a ResponseEntity containing the book if found, or a NOT_FOUND status
     *         if the book does not exist
     * @see ResponseEntity
     * @see PathVariable
     * 
     */
    // how to call this endpoint
    // GET http://localhost:8080/api/books/{id}
    // where {id} is the id of the book you want to retrieve
    // Example: GET http://localhost:8080/api/books/1
    // This will return the book with id 1 if it exists, or a 404 Not Found status
    // if it does not exist
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // how to call this endpoint
    // POST http://localhost:8080/api/books
    // with a JSON body containing the book details
    // Example: POST http://localhost:8080/api/books
    // {
    // "title": "The Great Gatsby",
    // "author": "F. Scott Fitzgerald",
    // "description": "A novel set in the 1920s about the American dream."
    // }
    // This will create a new book with the provided details
    // and return the created book with a 201 Created status
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setDescription(bookDetails.getDescription());
                    Book updatedBook = bookRepository.save(book);
                    return ResponseEntity.ok().body(updatedBook);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}