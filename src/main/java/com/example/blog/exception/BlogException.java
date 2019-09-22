package com.example.blog.exception;

public class BlogException extends Exception {

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable e) {
        super(message, e);
    }
}
