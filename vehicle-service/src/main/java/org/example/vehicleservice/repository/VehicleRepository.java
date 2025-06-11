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

    Optional<Vehicle> findByLicensePlate(String licensePlate);

    List<Vehicle> findByOwnerId(String ownerId);

    List<Vehicle> findByStatus(Vehicle.VehicleStatus status);

    List<Vehicle> findByType(Vehicle.VehicleType type);

    List<Vehicle> findByMake(String make);

    List<Vehicle> findByMakeAndModel(String make, String model);

    Optional<Vehicle> findByCurrentParkingSpaceId(String parkingSpaceId);

    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.ownerId = :ownerId AND v.status = :status")
    Long countByOwnerIdAndStatus(@Param("ownerId") String ownerId, @Param("status") Vehicle.VehicleStatus status);

    @Query("SELECT v FROM Vehicle v WHERE v.ownerId = :ownerId AND v.status IN :statuses")
    List<Vehicle> findByOwnerIdAndStatusIn(@Param("ownerId") String ownerId, @Param("statuses") List<Vehicle.VehicleStatus> statuses);
}
