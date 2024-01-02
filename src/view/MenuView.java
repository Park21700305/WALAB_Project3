package view;

import domain.Menu;
import domain.User;
import service.MenuService;

import java.util.Scanner;
import java.util.List;

public class MenuView {
    private final Scanner scanner;
    private final MenuService menuService;

    public MenuView(MenuService menuService, Scanner scanner) {
        this.menuService = menuService;
        this.scanner = scanner;
    }

    public void showMenus() {
        List<Menu> menus = menuService.getMenus();

        System.out.println("메뉴 목록:");

        for (int i = 0; i < menus.size(); i++) {
            Menu menu = menus.get(i);
            System.out.println((i + 1) + ". " + menu.getMenuName() + " - 가격: " + menu.getPrice() + "원");
        }
    }

    public void addOrderToUser(User user) {
        showMenus();
        System.out.print("메뉴 선택 (메뉴의 번호와 개수를 입력하세요 => 예: 1 2): ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        try {
            Menu selectedMenu = menuService.getMenus().get(Integer.parseInt(parts[0]) - 1);
            int quantity = Integer.parseInt(parts[1]);

            menuService.addMenuOrderToUser(user, selectedMenu, quantity);
            System.out.println("메뉴가 추가되었습니다.");
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
        }
    }
}
