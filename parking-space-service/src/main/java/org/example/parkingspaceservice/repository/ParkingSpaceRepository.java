package org.example.parkingspaceservice.repository;


import org.example.parkingspaceservice.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

    List<ParkingSpace> findByStatus(ParkingSpace.SpaceStatus status);

    List<ParkingSpace> findByCity(String city);

    List<ParkingSpace> findByZone(String zone);

    List<ParkingSpace> findByOwnerId(String ownerId);

    List<ParkingSpace> findByCityAndZone(String city, String zone);

    List<ParkingSpace> findByStatusAndCity(ParkingSpace.SpaceStatus status, String city);

    List<ParkingSpace> findByStatusAndZone(ParkingSpace.SpaceStatus status, String zone);

    List<ParkingSpace> findByType(ParkingSpace.SpaceType type);

    Optional<ParkingSpace> findBySpaceNumber(String spaceNumber);

    Optional<ParkingSpace> findByCurrentVehicleId(String vehicleId);

    @Query("SELECT COUNT(p) FROM ParkingSpace p WHERE p.city = :city AND p.status = :status")
    Long countByStatusAndCity(@Param("status") ParkingSpace.SpaceStatus status, @Param("city") String city);

    @Query("SELECT COUNT(p) FROM ParkingSpace p WHERE p.zone = :zone AND p.status = :status")
    Long countByStatusAndZone(@Param("status") ParkingSpace.SpaceStatus status, @Param("zone") String zone);

    @Query("SELECT COUNT(p) FROM ParkingSpace p WHERE p.ownerId = :ownerId AND p.status = :status")
    Long countByStatusAndOwnerId(@Param("status") ParkingSpace.SpaceStatus status, @Param("ownerId") String ownerId);
}
