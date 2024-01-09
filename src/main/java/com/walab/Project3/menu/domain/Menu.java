package com.walab.Project3.menu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Menu {
    @Id
    @Column(name = "menu_id")
    private Long id;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_price")
    private Integer price;
}
