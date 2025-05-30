package com.jaypal.BookAPI.controller;

import com.jaypal.BookAPI.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();
    private Long nextId = 1L;

    // GET /api/books - Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookList;
    }

    // POST /api/books - Add a new book
    @PostMapping
    public Book addBook(@RequestBody Book newBook) {
        newBook.setId(nextId++);
        bookList.add(newBook);
        return newBook;
    }

}
