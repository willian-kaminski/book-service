package com.kaminski.book.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateAuthToken(Authentication authentication);

    Boolean authTokenIsValid(String authToken);

    String getSubject(String authToken);

}
