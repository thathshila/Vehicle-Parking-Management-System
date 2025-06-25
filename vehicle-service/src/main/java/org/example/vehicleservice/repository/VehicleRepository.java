package org.example.vehicleservice.repository;

import org.example.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByIsDeletedFalse();

    List<Vehicle> findByIsDeletedTrue();

    Optional<Vehicle> findByIdAndIsDeletedFalse(Long id);

    Optional<Vehicle> findByLicensePlateAndIsDeletedFalse(String licensePlate);

    List<Vehicle> findByOwnerIdAndIsDeletedFalse(String ownerId);

    List<Vehicle> findByStatusAndIsDeletedFalse(Vehicle.VehicleStatus status);

    List<Vehicle> findByTypeAndIsDeletedFalse(Vehicle.VehicleType type);

    Optional<Vehicle> findByCurrentParkingSpaceIdAndIsDeletedFalse(String parkingSpaceId);

    List<Vehicle> findByOwnerIdAndStatusInAndIsDeletedFalse(String ownerId, List<Vehicle.VehicleStatus> statuses);

    Long countByOwnerIdAndStatusAndIsDeletedFalse(String ownerId, Vehicle.VehicleStatus status);

    @Query("SELECT v FROM Vehicle v WHERE v.ownerId = :ownerId")
    List<Vehicle> findAllByOwnerIdIncludingDeleted(@Param("ownerId") String ownerId);

    boolean existsByLicensePlate(String licensePlate);
}