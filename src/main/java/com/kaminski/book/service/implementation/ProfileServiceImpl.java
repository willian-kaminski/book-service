package com.kaminski.book.service.implementation;

import com.kaminski.book.entity.Profile;
import com.kaminski.book.repository.ProfileRepository;
import com.kaminski.book.service.ProfileService;
import com.kaminski.book.utils.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;
    private Validation validation;

    @Override
    public Profile getProfile(Integer id) {
        validation.verifyIfProfileExists(id);
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isPresent())
            return profile.get();
        return null;
    }

    @Override
    public Profile getProfileByName(String name) {
        validation.verifyIfProfileExists(name);
        Optional<Profile> profile = profileRepository.findByName(name);
        if(profile.isPresent())
            return profile.get();
        return null;
    }

}
