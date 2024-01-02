import domain.User;
import service.MenuService;
import service.UserService;
import view.MenuView;
import view.UserView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        MenuService menuService = new MenuService();
        UserView userView = new UserView(userService, scanner);
        MenuView menuView = new MenuView(menuService, scanner);

        User loggedInUser = null;

        while (true) {
            if (loggedInUser == null) {
                System.out.println("1.회원가입 2.로그인 3.회원삭제 4.회원목록 5.메뉴보기 6.시스템종료");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        userView.createUser();
                        break;
                    case 2:
                        loggedInUser = userView.login();
                        break;
                    case 3:
                        userView.removeUser();
                        break;
                    case 4:
                        userView.listAllUsers();
                        break;
                    case 5:
                        menuView.showMenus();
                        break;
                    case 6:
                        System.out.println("시스템을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }
            } else {
                System.out.println("1.메뉴선택 2.내정보수정 3.로그아웃 4.주문내역보기 5.주문삭제");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        menuView.addOrderToUser(loggedInUser);
                        break;
                    case 2:
                        userView.updateUserInfo(loggedInUser);
                        break;
                    case 3:
                        loggedInUser = null;
                        System.out.println("-----로그아웃 되었습니다.-----");
                        System.out.println("-------------------------");
                        break;
                    case 4:
                        userView.viewUserDetails(loggedInUser.getEmail());
                        break;
                    case 5:
                        userView.removeOrder(loggedInUser);
                        break;
                }
            }
        }
    }
}
