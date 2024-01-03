package domain;

import lombok.*;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MenuOrder {
    private Menu menu;
    private int quantity;
}
