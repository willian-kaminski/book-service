package com.kaminski.book.repository;

import com.kaminski.book.entity.UserPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPlatformRepository extends JpaRepository<UserPlatform, Integer> {

    Optional<UserPlatform> findByUsername(String username);

}
