package com.example.service;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookByName(String name) {
        return bookRepository.findBookByTitle(name);
    }

    public Optional<Book> getBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getByAuthorAndStatus(String name, String status) {
        return bookRepository.findByAuthorAndStatus(name, status);
    }

    public Page<Book> getBookAuthorDetailsWithPagination(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Book> getBookAuthorDetailsSortByTitleWithPagination(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size, Sort.by("title").ascending()));
    }

    public List<Book> getBookByTitleWithPagination(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findBookByTitle(title, pageable) ;
    }
}
