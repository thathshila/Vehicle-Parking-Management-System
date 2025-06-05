package org.example.parkingspaceservice.repository;


import org.example.parkingspaceservice.model.ParkingSpace;
import org.example.parkingspaceservice.model.SpaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

    List<ParkingSpace> findByStatus(SpaceStatus status);

    List<ParkingSpace> findByCity(String city);

    List<ParkingSpace> findByZone(String zone);

    List<ParkingSpace> findByOwnerId(String ownerId);

    List<ParkingSpace> findByCityAndStatus(String city, SpaceStatus status);

    List<ParkingSpace> findByZoneAndStatus(String zone, SpaceStatus status);

    Optional<ParkingSpace> findBySpaceNumber(String spaceNumber);

    @Query("SELECT p FROM ParkingSpace p WHERE p.city = :city AND p.zone = :zone AND p.status = :status")
    List<ParkingSpace> findAvailableSpacesByCityAndZone(@Param("city") String city,
                                                        @Param("zone") String zone,
                                                        @Param("status") SpaceStatus status);
}