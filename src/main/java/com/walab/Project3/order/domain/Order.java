package com.walab.Project3.order.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @OneToOne(cascade = CascadeType.ALL)
    private Long menuId;

    private Integer quantity;

}
