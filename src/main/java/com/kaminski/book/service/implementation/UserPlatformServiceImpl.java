package com.kaminski.book.service.implementation;

import com.kaminski.book.entity.UserPlatform;
import com.kaminski.book.repository.UserPlatformRepository;
import com.kaminski.book.service.UserPlatformService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPlatformServiceImpl implements UserPlatformService {

    private UserPlatformRepository userPlatformRepository;

    @Override
    public UserPlatform create(UserPlatform userPlatform) {
        return userPlatformRepository.save(userPlatform);
    }

    @Override
    public UserPlatform getUserPlatform(Integer id) {
        Optional<UserPlatform> userPlatform = userPlatformRepository.findById(id);
        if(userPlatform.isPresent())
            return userPlatform.get();
        return null;
    }

    @Override
    public UserPlatform getUserPlatformByUsername(String username) {
        Optional<UserPlatform> userPlatform = userPlatformRepository.findByUsername(username);
        if(userPlatform.isPresent())
            return userPlatform.get();
        return null;
    }

}
