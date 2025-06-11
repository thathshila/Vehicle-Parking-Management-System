package org.example.vehicleservice.service;


import jakarta.transaction.Transactional;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Optional<Vehicle> getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate);
    }

    public List<Vehicle> getVehiclesByOwner(String ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }

    public List<Vehicle> getVehiclesByStatus(Vehicle.VehicleStatus status) {
        return vehicleRepository.findByStatus(status);
    }

    public List<Vehicle> getVehiclesByType(Vehicle.VehicleType type) {
        return vehicleRepository.findByType(type);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setMake(vehicleDetails.getMake());
                    vehicle.setModel(vehicleDetails.getModel());
                    vehicle.setColor(vehicleDetails.getColor());
                    vehicle.setYear(vehicleDetails.getYear());
                    vehicle.setType(vehicleDetails.getType());
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle simulateEntry(Long id, String parkingSpaceId) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setStatus(Vehicle.VehicleStatus.PARKED_IN_SPACE);
                    vehicle.setCurrentParkingSpaceId(parkingSpaceId);
                    vehicle.setEntryTime(LocalDateTime.now());
                    vehicle.setExitTime(null);
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle simulateExit(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setStatus(Vehicle.VehicleStatus.PARKED_OUTSIDE);
                    vehicle.setCurrentParkingSpaceId(null);
                    vehicle.setExitTime(LocalDateTime.now());
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle reserveSpace(Long id, String parkingSpaceId) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setStatus(Vehicle.VehicleStatus.RESERVED_SPACE);
                    vehicle.setCurrentParkingSpaceId(parkingSpaceId);
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Optional<Vehicle> findVehicleInParkingSpace(String parkingSpaceId) {
        return vehicleRepository.findByCurrentParkingSpaceId(parkingSpaceId);
    }

    public List<Vehicle> getParkedVehiclesByOwner(String ownerId) {
        List<Vehicle.VehicleStatus> parkedStatuses = List.of(
                Vehicle.VehicleStatus.PARKED_IN_SPACE,
                Vehicle.VehicleStatus.RESERVED_SPACE
        );
        return vehicleRepository.findByOwnerIdAndStatusIn(ownerId, parkedStatuses);
    }

    public Long getParkedVehicleCount(String ownerId) {
        return vehicleRepository.countByOwnerIdAndStatus(ownerId, Vehicle.VehicleStatus.PARKED_IN_SPACE);
    }
}
