package com.example.controller;

import com.example.entity.Book;
import com.example.exception.BookAuthorNotFoundException;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book_author/v1")
public class BookAuthorController {

    private final BookService bookService;

    @Autowired
    public BookAuthorController(BookService bookService) {

        this.bookService = bookService;
    }

    //http://localhost:8080/book_author/v1/getBook/David

    @GetMapping("/getBook/{bookName}")
    public Book getBookByName(@PathVariable("bookName") String name) {
        return bookService.getBookByName(name)
                .orElseThrow(()-> new BookAuthorNotFoundException("Book Author not found with name: " + name));
    }
}
