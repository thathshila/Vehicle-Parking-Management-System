package org.example.paymentservice.service;

import jakarta.transaction.Transactional;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private Random random = new Random();

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Optional<Payment> getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }

    public List<Payment> getPaymentsByUser(String userId) {
        return paymentRepository.findByUserId(userId);
    }

    public List<Payment> getPaymentsByStatus(Payment.PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    public Payment createPayment(Payment payment) {
        payment.setStatus(Payment.PaymentStatus.PENDING);
        return paymentRepository.save(payment);
    }

    public Payment processPayment(Long id, String cardNumber, String cardHolderName,
                                  String expiryDate, String cvv) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    // Simulate payment processing
                    payment.setStatus(Payment.PaymentStatus.PROCESSING);
                    payment.setCardNumber(maskCardNumber(cardNumber));
                    payment.setCardHolderName(cardHolderName);

                    // Simulate payment gateway response
                    boolean paymentSuccess = simulatePaymentGateway();

                    if (paymentSuccess) {
                        payment.setStatus(Payment.PaymentStatus.COMPLETED);
                        payment.setPaymentTime(LocalDateTime.now());
                        payment.setPaymentGatewayResponse("Payment successful - Gateway Response: SUCCESS_" +
                                System.currentTimeMillis());
                    } else {
                        payment.setStatus(Payment.PaymentStatus.FAILED);
                        payment.setPaymentGatewayResponse("Payment failed - Gateway Response: FAILED_" +
                                System.currentTimeMillis());
                    }

                    return paymentRepository.save(payment);
                })
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public Payment refundPayment(Long id) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    if (payment.getStatus() != Payment.PaymentStatus.COMPLETED) {
                        throw new RuntimeException("Only completed payments can be refunded");
                    }
                    payment.setStatus(Payment.PaymentStatus.REFUNDED);
                    payment.setPaymentGatewayResponse("Refund processed - Gateway Response: REFUND_" +
                            System.currentTimeMillis());
                    return paymentRepository.save(payment);
                })
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public Payment cancelPayment(Long id) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    if (payment.getStatus() == Payment.PaymentStatus.COMPLETED) {
                        throw new RuntimeException("Completed payments cannot be cancelled");
                    }
                    payment.setStatus(Payment.PaymentStatus.CANCELLED);
                    return paymentRepository.save(payment);
                })
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public List<Payment> getPaymentsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByPaymentTimeBetween(startDate, endDate);
    }

    public Double getTotalRevenue() {
        return paymentRepository.getTotalAmountByStatus(Payment.PaymentStatus.COMPLETED);
    }

    public Double getUserTotalPayments(String userId) {
        return paymentRepository.getTotalAmountByUserId(userId);
    }

    public Long getUserPaymentCount(String userId, Payment.PaymentStatus status) {
        return paymentRepository.countByUserIdAndStatus(userId, status);
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }

    private boolean simulatePaymentGateway() {
        // Simulate 90% success rate
        return random.nextDouble() < 0.9;
    }

    public Payment validateMockPayment(Payment payment, String cardNumber, String expiryDate, String cvv) {
        // Mock validation logic
        if (cardNumber == null || cardNumber.length() < 13) {
            throw new RuntimeException("Invalid card number");
        }
        if (expiryDate == null || !expiryDate.matches("\\d{2}/\\d{2}")) {
            throw new RuntimeException("Invalid expiry date format (MM/YY)");
        }
        if (cvv == null || cvv.length() < 3) {
            throw new RuntimeException("Invalid CVV");
        }

        return payment;
    }
}
