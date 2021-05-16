package com.kaminski.book.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter @SuperBuilder
public class ExceptionDetails {

    private String title;
    private int status;
    private String detail;
    private Long timestamp;
    private String message;

}
