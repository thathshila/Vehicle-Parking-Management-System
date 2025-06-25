package org.example.paymentservice.repository;


import org.example.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(String transactionId);

    List<Payment> findByUserId(String userId);

    List<Payment> findByStatus(Payment.PaymentStatus status);

    @Query("SELECT p FROM Payment p WHERE p.paymentTime BETWEEN :startDate AND :endDate")
    List<Payment> findByPaymentTimeBetween(@Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.status = :status")
    Double getTotalAmountByStatus(@Param("status") Payment.PaymentStatus status);

    @Query("SELECT COUNT(p) FROM Payment p WHERE p.userId = :userId AND p.status = :status")
    Long countByUserIdAndStatus(@Param("userId") String userId, @Param("status") Payment.PaymentStatus status);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.userId = :userId AND p.status = 'COMPLETED'")
    Double getTotalAmountByUserId(@Param("userId") String userId);
}
