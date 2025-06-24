package org.example.paymentservice.service;

import jakarta.transaction.Transactional;
import org.example.paymentservice.entity.Receipt;
import org.example.paymentservice.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Optional<Receipt> getReceiptById(Long id) {
        return receiptRepository.findById(id);
    }

    public Optional<Receipt> getReceiptByNumber(String receiptNumber) {
        return receiptRepository.findByReceiptNumber(receiptNumber);
    }

    public Optional<Receipt> getReceiptByPaymentId(String paymentId) {
        return receiptRepository.findByPaymentId(paymentId);
    }

    public List<Receipt> getReceiptsByUser(String userId) {
        return receiptRepository.findByUserId(userId);
    }

    public Receipt generateReceipt(String paymentId, String userId, String parkingSpaceId,
                                   String vehicleId, Double amount, Double hourlyRate,
                                   LocalDateTime startTime, LocalDateTime endTime) {
        Receipt receipt = new Receipt(paymentId, userId, parkingSpaceId, vehicleId, amount, startTime, endTime);

        // Calculate parking duration
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            receipt.setParkingDurationMinutes(duration.toMinutes());
        }

        // Calculate tax (10% tax rate)
        Double taxAmount = amount * 0.10;
        receipt.setTaxAmount(taxAmount);
        receipt.setTotalAmount(amount + taxAmount);
        receipt.setHourlyRate(hourlyRate);

        // Generate receipt content
        String receiptData = generateReceiptContent(receipt);
        receipt.setReceiptData(receiptData);

        return receiptRepository.save(receipt);
    }

    public List<Receipt> getReceiptsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return receiptRepository.findByGeneratedAtBetween(startDate, endDate);
    }

    public List<Receipt> getUserReceiptsByDateRange(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return receiptRepository.findByUserIdAndDateRange(userId, startDate, endDate);
    }

    private String generateReceiptContent(Receipt receipt) {
        StringBuilder content = new StringBuilder();
        content.append("=== SMART PARKING RECEIPT ===\n");
        content.append("Receipt Number: ").append(receipt.getReceiptNumber()).append("\n");
        content.append("Payment ID: ").append(receipt.getPaymentId()).append("\n");
        content.append("User ID: ").append(receipt.getUserId()).append("\n");
        content.append("Vehicle ID: ").append(receipt.getVehicleId()).append("\n");
        content.append("Parking Space: ").append(receipt.getParkingSpaceId()).append("\n");
        content.append("Start Time: ").append(receipt.getParkingStartTime()).append("\n");
        content.append("End Time: ").append(receipt.getParkingEndTime()).append("\n");

        if (receipt.getParkingDurationMinutes() != null) {
            long hours = receipt.getParkingDurationMinutes() / 60;
            long minutes = receipt.getParkingDurationMinutes() % 60;
            content.append("Duration: ").append(hours).append("h ").append(minutes).append("m\n");
        }

        content.append("Hourly Rate: $").append(String.format("%.2f", receipt.getHourlyRate())).append("\n");
        content.append("Parking Fee: $").append(String.format("%.2f", receipt.getAmount())).append("\n");
        content.append("Tax (10%): $").append(String.format("%.2f", receipt.getTaxAmount())).append("\n");
        content.append("Total Amount: $").append(String.format("%.2f", receipt.getTotalAmount())).append("\n");
        content.append("Generated: ").append(receipt.getGeneratedAt()).append("\n");
        content.append("==============================\n");
        content.append("Thank you for using Smart Parking!\n");

        return content.toString();
    }
}
