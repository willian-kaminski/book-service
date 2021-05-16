package com.kaminski.book.service.implementation;

import com.kaminski.book.entity.UserPlatform;
import com.kaminski.book.repository.UserPlatformRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private UserPlatformRepository userPlatformRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserPlatform> userPlatform = userPlatformRepository.findByUsername(s);
        if(userPlatform.isPresent()){
            return userPlatform.get();
        }
        throw new UsernameNotFoundException("User or Password incorrect");
    }

}
