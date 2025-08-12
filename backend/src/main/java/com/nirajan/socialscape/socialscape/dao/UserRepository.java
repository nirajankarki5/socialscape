package com.nirajan.socialscape.socialscape.dao;

import com.nirajan.socialscape.socialscape.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
