package com.kaminski.book.service.implementation;

import com.kaminski.book.entity.UserPlatform;
import com.kaminski.book.repository.UserPlatformRepository;
import com.kaminski.book.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private UserPlatformRepository userPlatformRepository;

    @Value("${api.jwt.secret}")
    private String secret;

    @Value("${api.jwt.expiration}")
    private String expiration;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String generateAuthToken(Authentication authentication) {

        UserPlatform user = (UserPlatform) authentication.getPrincipal();
        var today = new Date();

        return Jwts.builder()
                .setIssuer(applicationName)
                .setSubject(user.getUsername())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    @Override
    public Boolean authTokenIsValid(String authToken) {
        if(authToken != null){
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(authToken);
            return true;
        }
        return false;
    }

    @Override
    public String getSubject(String authToken) {
        var claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(authToken).getBody();
        return claims.getSubject();
    }

}
