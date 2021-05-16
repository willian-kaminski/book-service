package com.kaminski.book.service;

import com.kaminski.book.entity.Profile;

public interface ProfileService {

    Profile getProfile(Integer id);

    Profile getProfileByName(String name);

}
