package domain;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String regDate;
    private List<MenuOrder> menuOrders;
    private Integer chargingTime;
    private Integer totalPrice;

    @Builder
    public User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.regDate = LocalDateTime.now().format(formatter);
        this.menuOrders = new ArrayList<>();
        this.chargingTime = 0;
        this.totalPrice = 0;
    }
}
