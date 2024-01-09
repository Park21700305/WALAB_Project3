package com.walab.Project3.User.domain;

import com.walab.Project3.seat.domain.Seat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
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
    private boolean isAdmin;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "register_date")
    private String regDate;

    @Column(name = "remaining_minutes")
    private Integer remainingMinutes;
    @Column(name = "total_price")
    private Integer totalPrice;

    // 0: 사용 종료, 1: 사용 중
    @Column(name = "in_use")
    private boolean inUse;

    public String getFormattedRemainingTime() {
        if (remainingMinutes == null || remainingMinutes <= 0) {
            return "0분";
        }
        int hours = remainingMinutes / 60;
        int minutes = remainingMinutes % 60;
        return String.format("%02d시간 %02d분", hours, minutes);
    }

    // Method to decrement the remaining time by one minute
    public void decrementRemainingTime() {
        if (remainingMinutes != null && remainingMinutes > 0) {
            remainingMinutes--;
        }
    }

    @Builder
    public User(String name, String password, String email, boolean isAdmin, Integer remainingMinutes, Integer totalPrice, boolean inUse) {
        this.name = name;
        this.password = password;
        this.email = email;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.regDate = LocalDateTime.now().format(formatter);
        this.isAdmin = isAdmin;
        this.remainingMinutes = remainingMinutes;
        this.totalPrice = totalPrice;
        this.inUse = inUse;
    }
}
