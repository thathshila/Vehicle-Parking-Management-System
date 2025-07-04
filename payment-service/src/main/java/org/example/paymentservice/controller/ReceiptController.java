package org.example.paymentservice.controller;

import org.example.paymentservice.entity.Receipt;
import org.example.paymentservice.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments/receipts")
@CrossOrigin(origins = "*")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        List<Receipt> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Long id) {
        return receiptService.getReceiptById(id)
                .map(receipt -> ResponseEntity.ok(receipt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{receiptNumber}")
    public ResponseEntity<Receipt> getReceiptByNumber(@PathVariable String receiptNumber) {
        return receiptService.getReceiptByNumber(receiptNumber)
                .map(receipt -> ResponseEntity.ok(receipt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Receipt> getReceiptByPaymentId(@PathVariable String paymentId) {
        return receiptService.getReceiptByPaymentId(paymentId)
                .map(receipt -> ResponseEntity.ok(receipt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Receipt>> getReceiptsByUser(@PathVariable String userId) {
        List<Receipt> receipts = receiptService.getReceiptsByUser(userId);
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Receipt>> getReceiptsByVehicle(@PathVariable String vehicleId) {
        List<Receipt> receipts = receiptService.getReceiptsByVehicle(vehicleId);
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/parking-space/{parkingSpaceId}")
    public ResponseEntity<List<Receipt>> getReceiptsByParkingSpace(@PathVariable String parkingSpaceId) {
        List<Receipt> receipts = receiptService.getReceiptsByParkingSpace(parkingSpaceId);
        return ResponseEntity.ok(receipts);
    }

    @PostMapping("/generate")
    public ResponseEntity<Receipt> generateReceipt(@RequestBody Map<String, Object> receiptRequest) {
        try {
            String paymentId = (String) receiptRequest.get("paymentId");
            String userId = (String) receiptRequest.get("userId");
            String parkingSpaceId = (String) receiptRequest.get("parkingSpaceId");
            String vehicleId = (String) receiptRequest.get("vehicleId");
            Double amount = Double.valueOf(receiptRequest.get("amount").toString());
            Double hourlyRate = Double.valueOf(receiptRequest.get("hourlyRate").toString());

            LocalDateTime startTime = null;
            LocalDateTime endTime = null;

            if (receiptRequest.get("startTime") != null) {
                startTime = LocalDateTime.parse(receiptRequest.get("startTime").toString());
            }
            if (receiptRequest.get("endTime") != null) {
                endTime = LocalDateTime.parse(receiptRequest.get("endTime").toString());
            }

            Receipt receipt = receiptService.generateReceipt(paymentId, userId, parkingSpaceId,
                    vehicleId, amount, hourlyRate, startTime, endTime);

            return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
