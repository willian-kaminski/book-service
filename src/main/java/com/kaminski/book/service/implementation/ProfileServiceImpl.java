package com.kaminski.book.service.implementation;

import com.kaminski.book.entity.Profile;
import com.kaminski.book.repository.ProfileRepository;
import com.kaminski.book.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Override
    public Profile getProfile(Integer id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isPresent())
            return profile.get();
        return null;
    }

    @Override
    public Profile getProfileByName(String name) {
        Optional<Profile> profile = profileRepository.findByName(name);
        if(profile.isPresent())
            return profile.get();
        return null;
    }

}
