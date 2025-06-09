package org.example.userservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_history")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String vehicleId;

    private String parkingSpaceId;

    private LocalDateTime bookingTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String paymentId;

    private LocalDateTime createdAt;

    public enum BookingStatus {
        ACTIVE, COMPLETED, CANCELLED, EXPIRED
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}