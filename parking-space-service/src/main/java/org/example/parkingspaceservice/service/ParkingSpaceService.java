package org.example.parkingspaceservice.service;

import org.example.parkingspaceservice.model.ParkingSpace;
import org.example.parkingspaceservice.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        return parkingSpaceRepository.findByStatus(ParkingSpace.SpaceStatus.AVAILABLE);
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

    public List<ParkingSpace> getAvailableSpacesByCity(String city) {
        return parkingSpaceRepository.findByStatusAndCity(ParkingSpace.SpaceStatus.AVAILABLE, city);
    }

    public List<ParkingSpace> getAvailableSpacesByZone(String zone) {
        return parkingSpaceRepository.findByStatusAndZone(ParkingSpace.SpaceStatus.AVAILABLE, zone);
    }

    public ParkingSpace createParkingSpace(ParkingSpace parkingSpace) {
        return parkingSpaceRepository.save(parkingSpace);
    }

    public ParkingSpace updateParkingSpace(Long id, ParkingSpace parkingSpaceDetails) {
        return parkingSpaceRepository.findById(id)
                .map(space -> {
                    space.setLocation(parkingSpaceDetails.getLocation());
                    space.setZone(parkingSpaceDetails.getZone());
                    space.setCity(parkingSpaceDetails.getCity());
                    space.setType(parkingSpaceDetails.getType());
                    space.setHourlyRate(parkingSpaceDetails.getHourlyRate());
                    space.setOwnerId(parkingSpaceDetails.getOwnerId());
                    return parkingSpaceRepository.save(space);
                })
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));
    }

    public ParkingSpace reserveSpace(Long id, String vehicleId) {
        return parkingSpaceRepository.findById(id)
                .map(space -> {
                    if (space.getStatus() != ParkingSpace.SpaceStatus.AVAILABLE) {
                        throw new RuntimeException("Parking space is not available for reservation");
                    }
                    space.setStatus(ParkingSpace.SpaceStatus.RESERVED);
                    space.setCurrentVehicleId(vehicleId);
                    return parkingSpaceRepository.save(space);
                })
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));
    }

    public ParkingSpace occupySpace(Long id, String vehicleId) {
        return parkingSpaceRepository.findById(id)
                .map(space -> {
                    if (space.getStatus() != ParkingSpace.SpaceStatus.AVAILABLE &&
                            space.getStatus() != ParkingSpace.SpaceStatus.RESERVED) {
                        throw new RuntimeException("Parking space is not available for occupation");
                    }
                    space.setStatus(ParkingSpace.SpaceStatus.OCCUPIED);
                    space.setCurrentVehicleId(vehicleId);
                    space.setOccupiedSince(LocalDateTime.now());
                    return parkingSpaceRepository.save(space);
                })
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));
    }

    public ParkingSpace releaseSpace(Long id) {
        return parkingSpaceRepository.findById(id)
                .map(space -> {
                    space.setStatus(ParkingSpace.SpaceStatus.AVAILABLE);
                    space.setCurrentVehicleId(null);
                    space.setOccupiedSince(null);
                    return parkingSpaceRepository.save(space);
                })
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));
    }

    public void deleteParkingSpace(Long id) {
        parkingSpaceRepository.deleteById(id);
    }

    public Long getAvailableSpaceCount(String city) {
        return parkingSpaceRepository.countByStatusAndCity(ParkingSpace.SpaceStatus.AVAILABLE, city);
    }

    public Long getOccupiedSpaceCount(String city) {
        return parkingSpaceRepository.countByStatusAndCity(ParkingSpace.SpaceStatus.OCCUPIED, city);
    }

    public Optional<ParkingSpace> findSpaceByVehicle(String vehicleId) {
        return parkingSpaceRepository.findByCurrentVehicleId(vehicleId);
    }
}
