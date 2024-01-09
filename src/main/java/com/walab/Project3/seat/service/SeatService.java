package com.walab.Project3.seat.service;

import com.walab.Project3.User.domain.User;
import com.walab.Project3.seat.domain.Seat;
import com.walab.Project3.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    // Initialize seats A1 to A10
    public void initializeSeats() {
        for (int num = 1; num <= 10; num++) {
            Seat seat = new Seat();
            seat.setSeatNumber("A" + num);
            seat.setUsed(false);
            seatRepository.save(seat);
        }
    }

    // Method for a user to pick a seat
    public void pickSeat(User user) {
        // Implementation for seat selection logic

    }

    // Method to add time for a seat reservation
    public void addTime(User user) {
        // Implementation for adding time to seat reservation
    }

    // Print all seats - for admin use
    public void printAllSeats() {
        seatRepository.findAll().forEach(seat -> {
            String status = seat.isUsed() ? "In Use" : "Available";
            System.out.println("Seat: " + seat.getSeatNumber() + ", Status: " + status);
        });
    }
}
