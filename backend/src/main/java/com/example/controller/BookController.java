package com.example.controller;

import com.example.model.Book;
import com.example.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    private IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        Iterable<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.save(book);
        return new ResponseEntity<>(newBook, CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Book> edit(@RequestBody Book book) {
        Book editBook = bookService.save(book);
        return new ResponseEntity<>(editBook, OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.findById(id).orElse(null);
        return new ResponseEntity<>(book, OK);
    }
}
