package com.kaminski.book.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AuthForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
