package com.kaminski.book.service.implementation;

import com.kaminski.book.exception.AuthException;
import com.kaminski.book.model.dto.TokenDTO;
import com.kaminski.book.model.form.AuthForm;
import com.kaminski.book.service.AuthService;
import com.kaminski.book.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    @Override
    public TokenDTO auth(AuthForm authForm, TokenService tokenService) {

        try {

            var dataAuth = new UsernamePasswordAuthenticationToken(authForm.getUsername(), authForm.getPassword());
            var authentication = authenticationManager.authenticate(dataAuth);
            String token = tokenService.generateAuthToken(authentication);

            return TokenDTO.builder().type("Bearer").hash(token).build();

        }catch (AuthenticationException authenticationException){
            throw new AuthException();
        }

    }

}
