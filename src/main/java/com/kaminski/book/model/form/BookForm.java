package com.kaminski.book.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BookForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String isbn;

    @NotEmpty
    private String description;

    @NotEmpty
    private String author;

    @Positive
    private int categoryId;

}