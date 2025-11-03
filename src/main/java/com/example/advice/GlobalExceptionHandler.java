package com.example.advice;

import com.example.error.ErrorResponse;
import com.example.exception.BookAuthorNotFoundException;
import com.example.exception.BookNotFoundException;
import com.example.exception.BookSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAuthorNotFoundException.class)
    public ResponseEntity<?> handleBookNotFound(BookAuthorNotFoundException bookAuthorNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), bookAuthorNotFoundException.getMessage(), "Book Author Not Found");
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFound(BookNotFoundException bookNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), bookNotFoundException.getMessage(), "Book Not Found");
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(BookSaveException.class)
    public ResponseEntity<?> handleBookSave(BookSaveException bookSaveException) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), bookSaveException.getMessage(), "Book Failed to save");
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
