package service;

import domain.Menu;
import domain.MenuOrder;
import domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuService {
    private final List<Menu> menus = Arrays.asList(Menu.values());

    public void addMenuOrderToUser(User user, Menu menu, int quantity) {
        MenuOrder menuOrder = new MenuOrder(menu, quantity);
        user.getMenuOrders().add(menuOrder);

        int totalPrice = user.getTotalPrice() + menu.getPrice() * quantity;
        user.setTotalPrice(totalPrice);

        if (menu.name().startsWith("TIME")) {
            String timeString = menu.getMenuName();

            int additionalTime = Integer.parseInt(timeString.replaceAll("[^0-9]", "")) * quantity;
            int newChargingTime = user.getChargingTime() + additionalTime;
            user.setChargingTime(newChargingTime);
        }

    }

    public List<Menu> getMenus() {
        return new ArrayList<>(menus);
    }
}
