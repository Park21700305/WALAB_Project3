package com.walab.Project3.seat.view;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class SeatView {

    private final SeatService seatService;

    public void pickSeat(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====================좌석 선택=====================");
        seatService.printAllSeats();
        System.out.print("선택할 좌석 번호를 입력하세요: ");
        String seatNumber = scanner.nextLine();

        seatService.pickSeat(user, seatNumber);
    }
}


