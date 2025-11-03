package com.example.controller;

import com.example.entity.Book;
import com.example.exception.BookNotFoundException;
import com.example.exception.BookSaveException;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book/v1")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        if (savedBook == null) {
            throw new BookSaveException("Failed to save book: " + book.getTitle());
        }
        return ResponseEntity.ok(savedBook);
    }

    //http://localhost:8080/book/v1/getBook/David

    @GetMapping("/getBook/{bookName}")
    public Book getBookByName(@PathVariable("bookName") String name) {
        return bookService.getBookByName(name)
                .orElseThrow(() -> new BookNotFoundException("Book not found with name: " + name));
    }

    //http://localhost:8080/book/v1/by-author?author=David

    @GetMapping("/by-author")
    public Book getBookByAuthor(@RequestParam("author") String author) {
        return bookService.getBookByAuthor(author)
                .orElseThrow(() -> new BookNotFoundException("Book not found by author: " + author));
    }

    //http://localhost:8080/book/v1/updateBook

    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        bookService.getBookById(book.getId())
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + book.getId()));
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    //http://localhost:8080/book/v1/deleteBook/1

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") Integer id) {
        bookService.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Records Deleted Successfully");
    }

    //http://localhost:8080/book/v1/getByAuthorAndStatus?name=Lincoln4&status=UnAvailable
    @GetMapping("/getByAuthorAndStatus")
    public List<Book> getByAuthorAndStatus(@RequestParam("name") String name, @RequestParam("status") String status) {
        List<Book> bookAuthorResult = bookService.getByAuthorAndStatus(name, status);
        if (bookAuthorResult == null)
            throw new BookNotFoundException("Author List not found with name:" + name + " and status: " + status);
        return bookAuthorResult;
    }

    //http://localhost:8080/book/v1/getAuthorWithPagination?&page=0&size=6
    @GetMapping("/getAuthorWithPagination")
    public Page<Book> getBookAuthorDetailsWithPagination(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Book> bookAuthorResult = bookService.getBookAuthorDetailsWithPagination(page, size);
        if (bookAuthorResult == null)
            throw new BookNotFoundException("Author List not found with pagination:");
        return bookAuthorResult;
    }

    //http://localhost:8080/book/v1/getAuthorSortByTitleWithPagination?&page=0&size=6
    @GetMapping("/getAuthorSortByTitleWithPagination")
    public Page<Book> getBookAuthorDetailsSortByTitleWithPagination(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Book> bookAuthorResult = bookService.getBookAuthorDetailsSortByTitleWithPagination(page, size);
        if (bookAuthorResult == null)
            throw new BookNotFoundException("Author List not found with pagination:");
        return bookAuthorResult;
    }

    //http://localhost:8080/book/v1/getBookByTitleWithPagination?&page=0&size=6&title=Math
    @GetMapping("/getBookByTitleWithPagination")
    public List<Book> getBookByTitleWithPagination(@RequestParam("title") String title, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<Book> bookAuthorResult = bookService.getBookByTitleWithPagination(title, page, size);
        if (bookAuthorResult.isEmpty())
            throw new BookNotFoundException("Author List not found with pagination:");
        return bookAuthorResult;
    }

}
