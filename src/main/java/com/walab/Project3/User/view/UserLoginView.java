package com.walab.Project3.User.view;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class UserLoginView {
    private final UserService userService;

    public User loginView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====================로그인=====================");

        System.out.print("이메일을 입력하세요: ");
        String email = scanner.nextLine();

        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        try {
            User loggedInUser = userService.loginUser(email, password);
            System.out.println("*로그인 완료!*");
            return loggedInUser;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
