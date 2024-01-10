package com.walab.Project3.seat.service;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.User.repository.UserRepository;
import com.walab.Project3.seat.domain.Seat;
import com.walab.Project3.seat.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    public void initializeSeats() {
        for (int num = 1; num <= 10; num++) {
            Seat seat = new Seat();
            seat.setSeatNumber("A" + num);
            seat.setUsed(false);
            seatRepository.save(seat);
        }
    }

    @Transactional
    public void pickSeat(User user, String seatNumber) {
        if (user.isInUse()) {
            System.out.println("이미 좌석을 선택하셨습니다. 좌석 변경은 관리자에게 문의하세요.");
            return;
        }

        Seat selectedSeat = seatRepository.findBySeatNumber(seatNumber);

        if (selectedSeat == null) {
            System.out.println("존재하지 않는 좌석 번호입니다.");
            return;
        }

        if (selectedSeat.isUsed()) {
            System.out.println("선택한 좌석은 이미 사용 중입니다.");
            return;
        }

        selectedSeat.setUser(user);
        selectedSeat.setUsed(true);
        user.setInUse(true);
        seatRepository.save(selectedSeat);
        user.setSeat(selectedSeat);
        System.out.println("좌석이 선택되었습니다: " + seatNumber);
        System.out.println("선택 좌석 => " + user.getSeat().getSeatNumber());
    }


    public void printAllSeats() {
        List<Seat> allSeats = seatRepository.findAll();
        System.out.println("좌석 상태");

        allSeats.forEach(seat -> {
            String seatDisplay = seat.isUsed() ? String.format("[%s - 사용중]", seat.getSeatNumber()) : String.format("[%s]", seat.getSeatNumber());
            System.out.print(seatDisplay + " ");
        });

        System.out.println("\n====================================================");
    }

    @Transactional
    public void changeSeat() {
        printAllSeats();

        Scanner scanner = new Scanner(System.in);
        System.out.print("기존 좌석 번호를 입력하세요: ");
        String currentSeatNumber = scanner.nextLine();

        Seat currentSeat = seatRepository.findBySeatNumber(currentSeatNumber);
        if (currentSeat == null) {
            System.out.println("존재하지 않는 좌석 번호입니다.");
            return;
        }

        if (!currentSeat.isUsed()) {
            System.out.println("해당 좌석은 사용 중이 아닙니다.");
            return;
        }

        User currentUser = currentSeat.getUser();
        if (currentUser == null) {
            System.out.println("해당 좌석에 연결된 사용자가 없습니다.");
            return;
        }

        System.out.print("변경할 새로운 좌석 번호를 입력하세요: ");
        String newSeatNumber = scanner.nextLine();

        Seat newSeat = seatRepository.findBySeatNumber(newSeatNumber);
        if (newSeat == null) {
            System.out.println("존재하지 않는 좌석 번호입니다.");
            return;
        }

        if (newSeat.isUsed()) {
            System.out.println("해당 좌석은 이미 사용 중입니다.");
            return;
        }

        currentSeat.setUsed(false);
        currentSeat.setUser(null);

        newSeat.setUsed(true);
        newSeat.setUser(currentUser);

        currentUser.setSeat(newSeat);

        seatRepository.save(currentSeat);
        seatRepository.save(newSeat);

        userRepository.save(currentUser);

        System.out.println("*좌석이 변경되었습니다.*");
    }
}
