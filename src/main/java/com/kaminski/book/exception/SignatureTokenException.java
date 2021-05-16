package com.kaminski.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SignatureTokenException extends RuntimeException{

    public SignatureTokenException() {
        super("Token invalid!");
    }

}
