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

    @Column(unique = true, length = 50)
    private String receiptNumber;

    @Column(length = 100)
    private String paymentId;

    @Column(length = 100)
    private String userId;

    @Column(length = 100)
    private String parkingSpaceId;

    @Column(length = 100)
    private String vehicleId;

    private Double amount;

    private Double taxAmount;

    private Double totalAmount;

    private LocalDateTime parkingStartTime;

    private LocalDateTime parkingEndTime;

    private Long parkingDurationMinutes;

    private Double hourlyRate;

    // Fix: Use TEXT or LONGTEXT for large receipt data
    @Column(columnDefinition = "TEXT")
    private String receiptData;

    private LocalDateTime generatedAt;

    public Receipt(String paymentId, String userId, String parkingSpaceId, String vehicleId, Double amount, LocalDateTime startTime, LocalDateTime endTime) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.parkingSpaceId = parkingSpaceId;
        this.vehicleId = vehicleId;
        this.amount = amount;
        this.parkingStartTime = startTime;
        this.parkingEndTime = endTime;
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