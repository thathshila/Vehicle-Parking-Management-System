package org.example.userservice.service;

import org.example.userservice.model.BookingHistory;
import org.example.userservice.repository.BookingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class BookingHistoryService {

    @Autowired
    private BookingHistoryRepository bookingHistoryRepository;

    public List<BookingHistory> getAllBookings() {
        return bookingHistoryRepository.findAll();
    }

    public Optional<BookingHistory> getBookingById(Long id) {
        return bookingHistoryRepository.findById(id);
    }

    public List<BookingHistory> getBookingsByUser(String userId) {
        return bookingHistoryRepository.findByUserId(userId);
    }

    public List<BookingHistory> getBookingsByVehicle(String vehicleId) {
        return bookingHistoryRepository.findByVehicleId(vehicleId);
    }

    public List<BookingHistory> getBookingsByParkingSpace(String parkingSpaceId) {
        return bookingHistoryRepository.findByParkingSpaceId(parkingSpaceId);
    }

    public List<BookingHistory> getActiveBookings() {
        return bookingHistoryRepository.findByStatus(BookingHistory.BookingStatus.ACTIVE);
    }

    public List<BookingHistory> getUserBookingsByDateRange(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingHistoryRepository.findByUserIdAndDateRange(userId, startDate, endDate);
    }

    public BookingHistory createBooking(BookingHistory booking) {
        return bookingHistoryRepository.save(booking);
    }

    public BookingHistory completeBooking(Long id, LocalDateTime endTime) {
        return bookingHistoryRepository.findById(id)
                .map(booking -> {
                    booking.setEndTime(endTime);
                    booking.setStatus(BookingHistory.BookingStatus.COMPLETED);
                    return bookingHistoryRepository.save(booking);
                })
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public BookingHistory cancelBooking(Long id) {
        return bookingHistoryRepository.findById(id)
                .map(booking -> {
                    booking.setStatus(BookingHistory.BookingStatus.CANCELLED);
                    return bookingHistoryRepository.save(booking);
                })
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public Long getUserBookingCount(String userId, BookingHistory.BookingStatus status) {
        return bookingHistoryRepository.countByUserIdAndStatus(userId, status);
    }
}

