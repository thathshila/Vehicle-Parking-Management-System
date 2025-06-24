package org.example.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String receiptNumber;

    private String paymentId;

    private String userId;

    private String parkingSpaceId;

    private String vehicleId;

    private Double amount;

    private Double taxAmount;

    private Double totalAmount;

    private LocalDateTime parkingStartTime;

    private LocalDateTime parkingEndTime;

    private Long parkingDurationMinutes;

    private Double hourlyRate;

    private String receiptData; // JSON or formatted receipt content

    private LocalDateTime generatedAt;

    public Receipt(String paymentId, String userId, String parkingSpaceId, String vehicleId, Double amount, LocalDateTime startTime, LocalDateTime endTime) {

    }

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
        if (receiptNumber == null) {
            receiptNumber = generateReceiptNumber();
        }
    }

    private String generateReceiptNumber() {
        return "RCP" + System.currentTimeMillis() + (int) (Math.random() * 1000);
    }

}

