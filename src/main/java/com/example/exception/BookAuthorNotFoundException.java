package com.example.exception;

public class BookAuthorNotFoundException extends RuntimeException{
    public BookAuthorNotFoundException( String message) {
        super(message);
    }
}
