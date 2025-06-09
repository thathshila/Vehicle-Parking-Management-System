package org.example.userservice.repository;



import org.example.userservice.model.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {

    List<BookingHistory> findByUserId(String userId);

    List<BookingHistory> findByVehicleId(String vehicleId);

    List<BookingHistory> findByParkingSpaceId(String parkingSpaceId);

    List<BookingHistory> findByStatus(BookingHistory.BookingStatus status);

    List<BookingHistory> findByUserIdAndStatus(String userId, BookingHistory.BookingStatus status);

    @Query("SELECT b FROM BookingHistory b WHERE b.userId = :userId AND b.bookingTime BETWEEN :startDate AND :endDate")
    List<BookingHistory> findByUserIdAndDateRange(@Param("userId") String userId,
                                                  @Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(b) FROM BookingHistory b WHERE b.userId = :userId AND b.status = :status")
    Long countByUserIdAndStatus(@Param("userId") String userId, @Param("status") BookingHistory.BookingStatus status);
}

