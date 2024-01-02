package service;

import domain.Menu;
import domain.MenuOrder;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public void createUser(Long id, String email, String password, String name) {
        if (isEmailExist(email)) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        User newUser = User.builder()
                .id(id++)
                .email(email)
                .password(password)
                .name(name)
                .build();

        users.add(newUser);
    }

    // 이메일 중복 체크
    public boolean isEmailExist(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    // 유저 삭제
    public void removeUser(String email, String password) {
        users.removeIf(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    // 유저 목록 조회
    public List<User> listAllUsers() {
        return new ArrayList<>(users);
    }

    // 유저 찾기
    public User findUser(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // 유저 정보 수정
    public void updateUser(User user, String email, String password, String name) {
        if (user != null) {
            if (email != null && !email.isEmpty()) user.setEmail(email);
            if (password != null && !password.isEmpty()) user.setPassword(password);
            if (name != null && !name.isEmpty()) user.setName(name);
        }
    }

    // 주문한 메뉴 취소
    public void removeOrder(User user, MenuOrder orderToRemove) {
        Menu menu = orderToRemove.getMenu();
        int quantity = orderToRemove.getQuantity();

        // 주문 목록에서 삭제
        user.getMenuOrders().removeIf(order -> order.equals(orderToRemove));

        // 총 가격 업데이트
        int priceReduction = menu.getPrice() * quantity;
        user.setTotalPrice(Math.max(user.getTotalPrice() - priceReduction, 0)); // 가격은 음수가 되지 않도록

        // 만약 시간 관련 메뉴인 경우, 시간도 업데이트
        if (menu.name().startsWith("TIME")) {
            String timeString = menu.getMenuName();
            int timeReduction = Integer.parseInt(timeString.replaceAll("[^0-9]", "")) * quantity;
            user.setChargingTime(Math.max(user.getChargingTime() - timeReduction, 0)); // 시간은 음수가 되지 않도록
        }
    }
}
