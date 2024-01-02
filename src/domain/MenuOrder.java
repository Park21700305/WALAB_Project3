package domain;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOrder {
    private Menu menu;
    private int quantity;
}
