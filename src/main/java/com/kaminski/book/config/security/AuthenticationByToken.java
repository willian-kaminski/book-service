package com.kaminski.book.config.security;

import com.kaminski.book.repository.UserPlatformRepository;
import com.kaminski.book.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationByToken extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserPlatformRepository userPlatformRepository;

    public AuthenticationByToken(TokenService tokenService, UserPlatformRepository userPlatformRepository) {
        this.tokenService = tokenService;
        this.userPlatformRepository = userPlatformRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String token = recoverHeaderToken(httpServletRequest);

            if(tokenService.authTokenIsValid(token)){
                authClient(token);
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);

        }catch (SignatureException e){
            logger.info("Token invalid");

        }catch (ExpiredJwtException e){
            logger.info("Token expired");
        }

    }

    private void authClient(String token) {

        String userSubject = tokenService.getSubject(token);
        var userPlatform = userPlatformRepository.findByUsername(userSubject).get();
        var authenticationToken = new UsernamePasswordAuthenticationToken(userPlatform, null, userPlatform.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    private String recoverHeaderToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7);

    }

}
