package org.example.paymentservice.repository;

import org.example.paymentservice.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findByReceiptNumber(String receiptNumber);

    Optional<Receipt> findByPaymentId(String paymentId);

    List<Receipt> findByUserId(String userId);

    List<Receipt> findByVehicleId(String vehicleId);

    List<Receipt> findByParkingSpaceId(String parkingSpaceId);

    @Query("SELECT r FROM Receipt r WHERE r.generatedAt BETWEEN :startDate AND :endDate")
    List<Receipt> findByGeneratedAtBetween(@Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);

    @Query("SELECT r FROM Receipt r WHERE r.userId = :userId AND r.generatedAt BETWEEN :startDate AND :endDate")
    List<Receipt> findByUserIdAndDateRange(@Param("userId") String userId,
                                           @Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);
}

