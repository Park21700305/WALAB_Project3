package com.walab.Project3.User.domain;

import com.walab.Project3.seat.domain.Seat;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class User {
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private Seat seat;
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

    @Column(name = "remaining_time")
    private LocalDateTime remainingTime;
    @Column(name = "total_price")
    private Integer totalPrice;

    // 0: 사용 종료, 1: 사용 중
    @Column(name = "in_use")
    private boolean inUse;
}
