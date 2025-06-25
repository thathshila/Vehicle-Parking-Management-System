package org.example.paymentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Transaction ID is required")
    @Column(unique = true)
    private String transactionId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Booking ID is required")
    private String bookingId;

    @NotBlank(message = "Parking space ID is required")
    private String parkingSpaceId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private String cardNumber;

    private String cardHolderName;

    private String paymentGatewayResponse;

    private LocalDateTime paymentTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, DIGITAL_WALLET, CASH, BANK_TRANSFER
    }

    public enum PaymentStatus {
        PENDING, PROCESSING, COMPLETED, FAILED, REFUNDED, CANCELLED
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (transactionId == null) {
            transactionId = generateTransactionId();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + (int) (Math.random() * 1000);
    }

}