package org.example.parkingspaceservice.service;


import org.example.parkingspaceservice.model.ParkingSpace;
import org.example.parkingspaceservice.model.SpaceStatus;
import org.example.parkingspaceservice.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public List<ParkingSpace> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll();
    }

    public Optional<ParkingSpace> getParkingSpaceById(Long id) {
        return parkingSpaceRepository.findById(id);
    }

    public Optional<ParkingSpace> getParkingSpaceByNumber(String spaceNumber) {
        return parkingSpaceRepository.findBySpaceNumber(spaceNumber);
    }

    public List<ParkingSpace> getAvailableSpaces() {
        return parkingSpaceRepository.findByStatus(SpaceStatus.AVAILABLE);
    }

    public List<ParkingSpace> getSpacesByCity(String city) {
        return parkingSpaceRepository.findByCity(city);
    }

    public List<ParkingSpace> getSpacesByZone(String zone) {
        return parkingSpaceRepository.findByZone(zone);
    }

    public List<ParkingSpace> getSpacesByOwner(String ownerId) {
        return parkingSpaceRepository.findByOwnerId(ownerId);
    }

    public List<ParkingSpace> getAvailableSpacesByCityAndZone(String city, String zone) {
        return parkingSpaceRepository.findAvailableSpacesByCityAndZone(city, zone, SpaceStatus.AVAILABLE);
    }

    public ParkingSpace createParkingSpace(ParkingSpace parkingSpace) {
        parkingSpace.setStatus(SpaceStatus.AVAILABLE);
        parkingSpace.setLastUpdated(LocalDateTime.now());
        return parkingSpaceRepository.save(parkingSpace);
    }

    public Optional<ParkingSpace> updateParkingSpace(Long id, ParkingSpace updatedSpace) {
        return parkingSpaceRepository.findById(id)
                .map(space -> {
                    space.setLocation(updatedSpace.getLocation());
                    space.setZone(updatedSpace.getZone());
                    space.setCity(updatedSpace.getCity());
                    space.setHourlyRate(updatedSpace.getHourlyRate());
                    space.setLastUpdated(LocalDateTime.now());
                    return parkingSpaceRepository.save(space);
                });
    }

    public Optional<ParkingSpace> reserveSpace(Long id, String userId) {
        return parkingSpaceRepository.findById(id)
                .filter(space -> space.getStatus() == SpaceStatus.AVAILABLE)
                .map(space -> {
                    space.setStatus(SpaceStatus.RESERVED);
                    space.setReservedBy(userId);
                    space.setReservationTime(LocalDateTime.now());
                    space.setLastUpdated(LocalDateTime.now());
                    return parkingSpaceRepository.save(space);
                });
    }

    public Optional<ParkingSpace> occupySpace(Long id) {
        return parkingSpaceRepository.findById(id)
                .filter(space -> space.getStatus() == SpaceStatus.RESERVED)
                .map(space -> {
                    space.setStatus(SpaceStatus.OCCUPIED);
                    space.setLastUpdated(LocalDateTime.now());
                    return parkingSpaceRepository.save(space);
                });
    }

    public Optional<ParkingSpace> releaseSpace(Long id) {
        return parkingSpaceRepository.findById(id)
                .filter(space -> space.getStatus() == SpaceStatus.OCCUPIED ||
                        space.getStatus() == SpaceStatus.RESERVED)
                .map(space -> {
                    space.setStatus(SpaceStatus.AVAILABLE);
                    space.setReservedBy(null);
                    space.setReservationTime(null);
                    space.setLastUpdated(LocalDateTime.now());
                    return parkingSpaceRepository.save(space);
                });
    }

    public boolean deleteParkingSpace(Long id) {
        return parkingSpaceRepository.findById(id)
                .map(space -> {
                    parkingSpaceRepository.delete(space);
                    return true;
                }).orElse(false);
    }
}