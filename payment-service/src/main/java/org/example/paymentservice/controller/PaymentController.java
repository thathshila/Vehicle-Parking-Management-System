package org.example.paymentservice.controller;

import jakarta.validation.Valid;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(payment -> ResponseEntity.ok(payment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable String transactionId) {
        return paymentService.getPaymentByTransactionId(transactionId)
                .map(payment -> ResponseEntity.ok(payment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable String userId) {
        List<Payment> payments = paymentService.getPaymentsByUser(userId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@PathVariable Payment.PaymentStatus status) {
        List<Payment> payments = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {
        try {
            Payment createdPayment = paymentService.createPayment(payment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<Payment> processPayment(@PathVariable Long id,
                                                  @RequestBody Map<String, String> paymentDetails) {
        try {
            String cardNumber = paymentDetails.get("cardNumber");
            String cardHolderName = paymentDetails.get("cardHolderName");
            String expiryDate = paymentDetails.get("expiryDate");
            String cvv = paymentDetails.get("cvv");

            Payment payment = paymentService.processPayment(id, cardNumber, cardHolderName, expiryDate, cvv);
            return ResponseEntity.ok(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<Payment> refundPayment(@PathVariable Long id) {
        try {
            Payment payment = paymentService.refundPayment(id);
            return ResponseEntity.ok(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Payment> cancelPayment(@PathVariable Long id) {
        try {
            Payment payment = paymentService.cancelPayment(id);
            return ResponseEntity.ok(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Payment> payments = paymentService.getPaymentsByDateRange(startDate, endDate);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/revenue/total")
    public ResponseEntity<Map<String, Double>> getTotalRevenue() {
        Double totalRevenue = paymentService.getTotalRevenue();
        Map<String, Double> response = Map.of("totalRevenue", totalRevenue != null ? totalRevenue : 0.0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/total")
    public ResponseEntity<Map<String, Double>> getUserTotalPayments(@PathVariable String userId) {
        Double totalPayments = paymentService.getUserTotalPayments(userId);
        Map<String, Double> response = Map.of("totalPayments", totalPayments != null ? totalPayments : 0.0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/stats")
    public ResponseEntity<Map<String, Long>> getUserPaymentStats(@PathVariable String userId) {
        Long completedCount = paymentService.getUserPaymentCount(userId, Payment.PaymentStatus.COMPLETED);
        Long pendingCount = paymentService.getUserPaymentCount(userId, Payment.PaymentStatus.PENDING);
        Long failedCount = paymentService.getUserPaymentCount(userId, Payment.PaymentStatus.FAILED);

        Map<String, Long> stats = Map.of(
                "completed", completedCount,
                "pending", pendingCount,
                "failed", failedCount,
                "total", completedCount + pendingCount + failedCount
        );

        return ResponseEntity.ok(stats);
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, String>> validatePaymentData(@RequestBody Map<String, String> paymentData) {
        try {
            String cardNumber = paymentData.get("cardNumber");
            String expiryDate = paymentData.get("expiryDate");
            String cvv = paymentData.get("cvv");

            Payment payment = new Payment();
            paymentService.validateMockPayment(payment, cardNumber, expiryDate, cvv);

            Map<String, String> response = Map.of("status", "valid", "message", "Payment data is valid");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> response = Map.of("status", "invalid", "message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}

