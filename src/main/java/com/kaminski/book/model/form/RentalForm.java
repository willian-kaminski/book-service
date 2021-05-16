package com.kaminski.book.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class RentalForm {

    @Positive
    private int bookId;

}
