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

    // Find all non-deleted vehicles
    List<Vehicle> findByIsDeletedFalse();

    // Find all deleted vehicles
    List<Vehicle> findByIsDeletedTrue();

    // Find by ID (non-deleted only)
    Optional<Vehicle> findByIdAndIsDeletedFalse(Long id);

    // Find by license plate (non-deleted only)
    Optional<Vehicle> findByLicensePlateAndIsDeletedFalse(String licensePlate);

    // Find by owner ID (non-deleted only)
    List<Vehicle> findByOwnerIdAndIsDeletedFalse(String ownerId);

    // Find by status (non-deleted only)
    List<Vehicle> findByStatusAndIsDeletedFalse(Vehicle.VehicleStatus status);

    // Find by type (non-deleted only)
    List<Vehicle> findByTypeAndIsDeletedFalse(Vehicle.VehicleType type);

    // Find by current parking space (non-deleted only)
    Optional<Vehicle> findByCurrentParkingSpaceIdAndIsDeletedFalse(String parkingSpaceId);

    // Find parked vehicles by owner (non-deleted only)
    List<Vehicle> findByOwnerIdAndStatusInAndIsDeletedFalse(String ownerId, List<Vehicle.VehicleStatus> statuses);

    // Count parked vehicles by owner (non-deleted only)
    Long countByOwnerIdAndStatusAndIsDeletedFalse(String ownerId, Vehicle.VehicleStatus status);

    // Custom query to find vehicles by owner including deleted status
    @Query("SELECT v FROM Vehicle v WHERE v.ownerId = :ownerId")
    List<Vehicle> findAllByOwnerIdIncludingDeleted(@Param("ownerId") String ownerId);

    // Check if license plate exists (excluding deleted)
    boolean existsByLicensePlateAndIsDeletedFalse(String licensePlate);

    // Check if license plate exists (including deleted)
    boolean existsByLicensePlate(String licensePlate);
}