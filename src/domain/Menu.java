package domain;

import javax.persistence.Entity;

@Entity
public enum Menu {
    MENU1("떡볶이", 3000),
    MENU2("라면", 2500),
    MENU3("콜라", 1000),
    MENU4("소떡소떡", 2000),
    TIME1("1시간", 1000),
    TIME2("2시간", 1800),
    TIME3("3시간", 2500);

    private final String menuName;
    private final Integer price;

    Menu(String menuName, Integer price) {
        this.menuName = menuName;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getPrice() {
        return price;
    }
}
