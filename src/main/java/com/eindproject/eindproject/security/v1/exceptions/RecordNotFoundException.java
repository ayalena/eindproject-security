package com.eindproject.eindproject.security.v1.exceptions;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Cannot find record: " + id);
    }

    public RecordNotFoundException() {
        super("record not found");
    }
}
