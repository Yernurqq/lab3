package com.example.lab3.controller;

import com.example.lab3.model.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @PostMapping
    public Book receiveBook(@RequestBody Book book) {
        book.setStatus("received");
        return book;
    }
}
