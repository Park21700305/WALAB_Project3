package com.walab.Project3.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Order {
    @Id
    private Long id;
    private Long userId;
    private Long menuId;

    private Integer quantity;

}
