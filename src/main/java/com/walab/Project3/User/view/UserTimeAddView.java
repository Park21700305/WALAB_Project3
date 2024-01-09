package com.walab.Project3.User.view;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class UserTimeAddView {
    private final UserService userService;

        public void timeAddView(User user) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n=====================시간 추가=====================");
            System.out.println("1. 30분 추가 1000원");
            System.out.println("2. 1시간 추가 2000원");
            System.out.println("3. 2시간 추가 3000원");
            System.out.println("4. 3시간 추가 4000원");
            System.out.println("5. 4시간 추가 5000원");
            System.out.println("6. 5시간 추가 6000원");
            System.out.println("0. 뒤로가기");
            System.out.print("번호를 입력하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> userService.timeAddUser(30, user);
                case 2 -> userService.timeAddUser(60, user);
                case 3 -> userService.timeAddUser(120, user);
                case 4 -> userService.timeAddUser(180, user);
                case 5 -> userService.timeAddUser(240, user);
                case 6 -> userService.timeAddUser(300, user);
                case 0 -> System.out.println("뒤로가기");
                default -> System.out.println("*잘못된 입력입니다.*");
            }
        }

}
