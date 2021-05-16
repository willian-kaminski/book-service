package com.kaminski.book.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter @SuperBuilder
public class ValidationErrorDetails extends ExceptionDetails{

    private String field;
    private String fieldMessage;

}
