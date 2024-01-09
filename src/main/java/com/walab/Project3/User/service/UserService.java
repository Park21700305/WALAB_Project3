package com.walab.Project3.User.service;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(String name, String email, String password) {
        User newUser = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .inUse(false)
                .remainingMinutes(0)
                .totalPrice(0)
                .isAdmin(Objects.equals(name, "admin") ? true : false)
                .build();

        userRepository.save(newUser);
    }

    // Method to log in a user
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        user.setInUse(true);
        userRepository.save(user);
        return user;
    }

    // Check if email is duplicate
    public boolean isEmailDuplicate(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Create an admin user
    public void createAdminUser(String name, String email, String password) {
        if (!isEmailDuplicate(email)) {
            registerUser(name, email, password);
        }
    }

    @Scheduled(fixedRate = 60000)
    public void decrementUserTimes() {
        List<User> usersInUse = userRepository.findAllByInUse(true);
        for (User user : usersInUse) {
            user.decrementRemainingTime();
            userRepository.save(user);
        }
    }

    // Print all users - for admin
    public void printAllUsers() {
        userRepository.findAll().forEach(user -> {
            // Print user details - customize as needed
            System.out.println("User: " + user.getName() + ", Email: " + user.getEmail());
        });
    }

    // Change a user's seat - for admin
    public void changeUserSeat(/* parameters if needed */) {
        // Implementation depends on how you manage seat assignments
        // This could involve retrieving a user and updating their seat information
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
