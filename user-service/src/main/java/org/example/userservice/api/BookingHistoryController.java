package org.example.userservice.api;

import org.example.userservice.model.BookingHistory;
import org.example.userservice.service.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/bookings")
@CrossOrigin(origins = "*")
public class BookingHistoryController {

    @Autowired
    private BookingHistoryService bookingHistoryService;

    @GetMapping
    public ResponseEntity<List<BookingHistory>> getAllBookings() {
        List<BookingHistory> bookings = bookingHistoryService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingHistory> getBookingById(@PathVariable Long id) {
        return bookingHistoryService.getBookingById(id)
                .map(booking -> ResponseEntity.ok(booking))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingHistory>> getBookingsByUser(@PathVariable String userId) {
        List<BookingHistory> bookings = bookingHistoryService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<BookingHistory>> getBookingsByVehicle(@PathVariable String vehicleId) {
        List<BookingHistory> bookings = bookingHistoryService.getBookingsByVehicle(vehicleId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/parking-space/{parkingSpaceId}")
    public ResponseEntity<List<BookingHistory>> getBookingsByParkingSpace(@PathVariable String parkingSpaceId) {
        List<BookingHistory> bookings = bookingHistoryService.getBookingsByParkingSpace(parkingSpaceId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/active")
    public ResponseEntity<List<BookingHistory>> getActiveBookings() {
        List<BookingHistory> bookings = bookingHistoryService.getActiveBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<BookingHistory> createBooking(@Validated @RequestBody BookingHistory booking) {
        try {
            BookingHistory createdBooking = bookingHistoryService.createBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<BookingHistory> completeBooking(@PathVariable Long id,
                                                          @RequestBody Map<String, String> request) {
        try {
            LocalDateTime endTime = LocalDateTime.parse(request.get("endTime"));
            BookingHistory booking = bookingHistoryService.completeBooking(id, endTime);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<BookingHistory> cancelBooking(@PathVariable Long id) {
        try {
            BookingHistory booking = bookingHistoryService.cancelBooking(id);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}/stats")
    public ResponseEntity<Map<String, Long>> getUserBookingStats(@PathVariable String userId) {
        Long activeCount = bookingHistoryService.getUserBookingCount(userId, BookingHistory.BookingStatus.ACTIVE);
        Long completedCount = bookingHistoryService.getUserBookingCount(userId, BookingHistory.BookingStatus.COMPLETED);
        Long cancelledCount = bookingHistoryService.getUserBookingCount(userId, BookingHistory.BookingStatus.CANCELLED);

        Map<String, Long> stats = Map.of(
                "active", activeCount,
                "completed", completedCount,
                "cancelled", cancelledCount,
                "total", activeCount + completedCount + cancelledCount
        );

        return ResponseEntity.ok(stats);
    }
}

