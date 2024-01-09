package com.walab.Project3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Scanner;

@SpringBootApplication
//@EnableJpaAuditing
@EnableScheduling // 차감시간 계산을 위해 스케줄러 사용
public class Project3Application {

    public static void main(String[] args) {
        SpringApplication.run(Project3Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("=============================================================");
                System.out.println("메뉴를 선택해주세요.");
                System.out.println("1. 로그인 2. 회원가입 3. 시간추가 4. 사용종료(로그아웃) 5. 관리자 로그인");
                System.out.println("=============================================================");

                int choice = scanner.nextInt();

                switch (choice) {
                       case 1 -> ;
                            break;
                        case 2:
                            System.out.println("회원가입");
                            break;
                        case 3:
                            System.out.println("시간추가");
                            break;
                        case 4:
                            System.out.println("사용종료(로그아웃)");
                            break;
                        case 5:
                            System.out.println("관리자 로그인");
                            break;
                        default:
                            System.out.println("잘못된 입력입니다.");
                            break;

                }
            }
        };
    }
}
