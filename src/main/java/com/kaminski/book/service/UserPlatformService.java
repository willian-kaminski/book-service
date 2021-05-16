package com.kaminski.book.service;

import com.kaminski.book.entity.UserPlatform;

public interface UserPlatformService {

    UserPlatform create(UserPlatform userPlatform);

    UserPlatform getUserPlatform(Integer id);

    UserPlatform getUserPlatformByUsername(String username);

}
