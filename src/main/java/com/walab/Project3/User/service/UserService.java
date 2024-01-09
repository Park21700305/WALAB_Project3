package com.walab.Project3.User.service;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.User.repository.UserRepository;
import com.walab.Project3.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SeatService seatService;

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
                .orElseThrow(() -> new IllegalArgumentException("*사용자가 존재하지 않습니다.*"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("*비밀번호가 일치하지 않습니다.*");
        }

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

    public void printAllUsers() {
        userRepository.findAll().forEach(user -> {
            String seatNumber = "null";
            if (user.getSeat() != null && user.getSeat().getSeatNumber() != null) {
                seatNumber = user.getSeat().getSeatNumber();
            }

            // Print user details - customize as needed
            System.out.println(
                    "이름: " + user.getName() + "," +
                            " 이메일: " + user.getEmail() + "," +
                            " 좌석: " + seatNumber + "," +
                            " 잔여시간: " + user.getRemainingMinutes() + "분," +
                            " 총가격: " + user.getTotalPrice() + "원," +
                            " 사용여부: " + user.isInUse() + "," +
                            " 관리자자격: " + user.isAdmin()
            );
        });
    }


    public void timeAddUser(int time, User user) {
        user.setRemainingMinutes(user.getRemainingMinutes() + time);
        if (time == 30) {
            user.setTotalPrice(user.getTotalPrice() + 1000);
        } else if (time == 60) {
            user.setTotalPrice(user.getTotalPrice() + 2000);
        } else if (time == 120) {
            user.setTotalPrice(user.getTotalPrice() + 3000);
        } else if (time == 180) {
            user.setTotalPrice(user.getTotalPrice() + 4000);
        } else if (time == 240) {
            user.setTotalPrice(user.getTotalPrice() + 5000);
        } else if (time == 300) {
            user.setTotalPrice(user.getTotalPrice() + 6000);
        }

        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
