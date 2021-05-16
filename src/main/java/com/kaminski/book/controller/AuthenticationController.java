package com.kaminski.book.controller;

import com.kaminski.book.model.dto.TokenDTO;
import com.kaminski.book.model.form.AuthForm;
import com.kaminski.book.service.AuthService;
import com.kaminski.book.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/login")
public class AuthenticationController {

    private AuthService authService;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid AuthForm authForm){
        return ResponseEntity.status(HttpStatus.OK).body(authService.auth(authForm, tokenService));
    }

}
