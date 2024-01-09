package com.walab.Project3;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.User.service.UserService;
import com.walab.Project3.User.view.UserLoginView;
import com.walab.Project3.User.view.UserRegisterView;
import com.walab.Project3.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class Project3Application {

    private final UserRegisterView userRegisterView;
    private final UserLoginView userLoginView;
    private final UserService userService;
    private final SeatService seatService;

    public static void main(String[] args) {
        SpringApplication.run(Project3Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Initialize admin user and seats
            userService.createAdminUser("admin", "admin@project3.com", "admin123");
            seatService.initializeSeats();

            Scanner scanner = new Scanner(System.in);
            User loggedInUser = null;

            while (true) {
                if (loggedInUser == null) {
                    System.out.println("1.회원가입 2.로그인 3.시스템종료");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> userRegisterView.registerView();

                        case 2 -> loggedInUser = userLoginView.loginView();

                        case 3 -> {
                            System.out.println("시스템을 종료합니다.");
                            System.exit(0);
                        }
                        default -> System.out.println("잘못된 입력입니다.");
                    }
                } else if (!loggedInUser.isAdmin()) {
                    System.out.println("1.좌석선택 2.시간추가 3.로그아웃 4.회원탈퇴");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> seatService.pickSeat(loggedInUser);

                        case 2 -> seatService.addTime(loggedInUser);

                        case 3 -> loggedInUser = null;

                        case 4 -> {
                            userService.deleteUser(loggedInUser);
                            loggedInUser = null;
                        }

                        default -> System.out.println("잘못된 입력입니다.");
                    }
                } else {
                    System.out.println("1.유저정보출력 2.유저좌석변경 3.좌석조회 4.로그아웃");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> userService.printAllUsers();

                        case 2 -> userService.changeUserSeat();

                        case 3 -> seatService.printAllSeats();

                        case 4 -> loggedInUser = null;

                        default -> System.out.println("잘못된 입력입니다.");
                    }


                }
            }
        };
    }
}
