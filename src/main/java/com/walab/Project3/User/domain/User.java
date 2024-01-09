package com.walab.Project3.User.domain;

import com.walab.Project3.order.domain.Order;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Order> orderList = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    // 0: 일반 사용자, 1: 관리자
    @Column(name = "is_admin")
    private Integer isAdmin;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "register_date")
    private LocalDateTime regDate;

    @Column(name = "charging_time")
    private Integer chargingTime;
    @Column(name = "total_price")
    private Integer totalPrice;
}
