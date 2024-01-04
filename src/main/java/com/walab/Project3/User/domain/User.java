package com.walab.Project3.User.domain;

import com.walab.Project3.order.domain.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "register_date")
    private LocalDateTime regDate;
    @OneToMany
    private List<Order> orderList;
    @Column(name = "charging_time")
    private  Integer chargingTime;
    @Column(name = "total_price")
    private Integer totalPrice;
}
