package com.eindproject.eindproject.security.v1.exceptions;

public class FileStorageException extends RuntimeException{

//    It’s thrown when an unexpected situation occurs while storing a file in the file system

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
