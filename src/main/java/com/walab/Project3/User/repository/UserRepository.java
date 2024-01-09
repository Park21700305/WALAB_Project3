package com.walab.Project3.User.repository;

import com.walab.Project3.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // Method to find all users who are currently logged in
    List<User> findAllByInUse(boolean inUse);
}
