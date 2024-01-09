package com.walab.Project3.User.view;

import com.walab.Project3.User.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class UserRegisterView {

    private final UserService userService;

    public void registerView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====================회원가입=====================");

        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();

        System.out.print("이메일을 입력하세요: ");
        String email = scanner.nextLine();

        while (userService.isEmailDuplicate(email)) {
            System.out.println("이미 사용 중인 이메일입니다. 다른 이메일을 입력해주세요.");
            email = scanner.nextLine();
        }

        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        // 여기서 사용자 정보를 생성하고 저장하는 로직을 추가
        userService.registerUser(name, email, password);

        System.out.println("=====================회원가입 완료==================");
    }
}
