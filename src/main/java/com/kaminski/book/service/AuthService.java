package com.kaminski.book.service;

import com.kaminski.book.model.dto.TokenDTO;
import com.kaminski.book.model.form.AuthForm;

public interface AuthService {

    TokenDTO auth(AuthForm authForm, TokenService tokenService);
}
