package view;

import domain.MenuOrder;
import domain.User;
import service.UserService;

import java.util.Scanner;

public class UserView {
    private static Long id = 1L;
    private final Scanner scanner;
    private final UserService userService;


    public UserView(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void createUser() {
        System.out.println("-----------회원가입-----------");
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        while (userService.isEmailExist(email)) {
            System.out.println("이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요.");
            System.out.print("이메일: ");
            email = scanner.nextLine();
        }

        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();

        userService.createUser(id++, email, password, name);
        System.out.println("회원가입 완료.");
        System.out.println("--------------------------");
    }

    public void removeUser() {
        System.out.println("-----------회원삭제-----------");
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("비밀번호(입력 시 정보가 삭제됩니다.): ");
        String password = scanner.nextLine();

        userService.removeUser(email, password);
        System.out.println("유저가 삭제되었습니다.");
        System.out.println("--------------------------");
    }

    public void listAllUsers() {
        System.out.println("-----------회원목록-----------");
        userService.listAllUsers().forEach(user ->
                System.out.println(user.getId() + ". " + "이메일: " + user.getEmail() + "  이름: " + user.getName()));
        System.out.println("----------------------------");
    }

    public void viewUserDetails(String email) {
        User user = userService.listAllUsers().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (user == null) {
            System.out.println("유저를 찾을 수 없습니다.");
        } else {
            System.out.println("-----------유저 정보-----------");
            System.out.println("이메일: " + user.getEmail());
            System.out.println("이름: " + user.getName());
            System.out.println("가입일: " + user.getRegDate());
            System.out.println("충전 시간: " + user.getChargingTime() + "시간");
            System.out.println("총 결제 금액: " + user.getTotalPrice() + "원");
            System.out.println("----------------------------");
            System.out.println("---주문내역---");
            user.getMenuOrders().forEach(order ->
                    System.out.println(order.getMenu().getMenuName() + " x " + order.getQuantity()));
            System.out.println("------------");
        }
    }

    public User login() {
        System.out.println("로그인");
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        User user = userService.findUser(email, password);
        if (user != null) {
            System.out.println("로그인 성공!");
            return user;
        } else {
            System.out.println("로그인 실패. 이메일 또는 비밀번호를 확인해주세요.");
            return null;
        }
    }

    public void updateUserInfo(User user) {
        System.out.println("정보 수정을 위해 새로운 정보를 입력해주세요. 변경하지 않을 항목은 엔터를 눌러 넘어가세요.");
        System.out.print("새 이메일: ");
        String newEmail = scanner.nextLine();
        System.out.print("새 비밀번호: ");
        String newPassword = scanner.nextLine();
        System.out.print("새 이름: ");
        String newName = scanner.nextLine();

        userService.updateUser(user, newEmail, newPassword, newName);
        System.out.println("정보가 수정되었습니다.");
    }

    public void removeOrder(User user) {
        if (user.getMenuOrders().isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }

        System.out.println("삭제할 주문을 선택해주세요.");
        for (int i = 0; i < user.getMenuOrders().size(); i++) {
            MenuOrder order = user.getMenuOrders().get(i);
            System.out.println((i + 1) + ". " + order.getMenu().getMenuName() + " x " + order.getQuantity());
        }
        System.out.print("선택: ");
        int orderIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (orderIndex >= 0 && orderIndex < user.getMenuOrders().size()) {
            userService.removeOrder(user, user.getMenuOrders().get(orderIndex));
            System.out.println("주문이 삭제되었습니다.");
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }
}
