package com.example.repository;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByTitle(String name);

    List<Book> findBookByTitle(String name, Pageable pageable);

    Optional<Book> findBookByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, String status);

}
