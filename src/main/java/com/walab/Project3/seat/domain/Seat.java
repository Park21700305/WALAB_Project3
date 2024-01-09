package com.walab.Project3.seat.domain;

import com.walab.Project3.User.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
// 프로그램 시작 시 자동으로 seatNumber A1 ~ A10 생성.
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "seat_number")
    private String seatNumber;
    @Column(name = "is_used")
    private boolean isUsed;
}
