package com.kaminski.book.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashGenerate {

    public String generateBCrypt(String string){
        return new BCryptPasswordEncoder().encode(string);
    }

}
