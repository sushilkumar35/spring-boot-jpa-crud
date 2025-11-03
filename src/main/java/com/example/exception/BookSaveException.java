package com.example.exception;

public class BookSaveException extends RuntimeException {
    public BookSaveException(String message) {
        super(message);
    }
}
