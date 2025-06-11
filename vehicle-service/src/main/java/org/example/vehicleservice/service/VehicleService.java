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
        return vehicleRepository.findByIsDeletedFalse();
    }

    public List<Vehicle> getAllVehiclesIncludingDeleted() {
        return vehicleRepository.findAll();
    }

    // Get all deleted vehicles
    public List<Vehicle> getAllDeletedVehicles() {
        return vehicleRepository.findByIsDeletedTrue();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findByIdAndIsDeletedFalse(id);
    }

    // Get vehicle by ID including deleted ones
    public Optional<Vehicle> getVehicleByIdIncludingDeleted(Long id) {
        return vehicleRepository.findById(id);
    }

    public Optional<Vehicle> getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlateAndIsDeletedFalse(licensePlate);
    }

    public List<Vehicle> getVehiclesByOwner(String ownerId) {
        return vehicleRepository.findByOwnerIdAndIsDeletedFalse(ownerId);
    }

    // Get vehicles by owner including deleted ones
    public List<Vehicle> getVehiclesByOwnerIncludingDeleted(String ownerId) {
        return vehicleRepository.findAllByOwnerIdIncludingDeleted(ownerId);
    }

    public List<Vehicle> getVehiclesByStatus(Vehicle.VehicleStatus status) {
        return vehicleRepository.findByStatusAndIsDeletedFalse(status);
    }

    public List<Vehicle> getVehiclesByType(Vehicle.VehicleType type) {
        return vehicleRepository.findByTypeAndIsDeletedFalse(type);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        // Check if license plate already exists (including deleted vehicles)
        if (vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())) {
            throw new RuntimeException("Vehicle with license plate " + vehicle.getLicensePlate() + " already exists");
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        return vehicleRepository.findByIdAndIsDeletedFalse(id)
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
        return vehicleRepository.findByIdAndIsDeletedFalse(id)
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
        return vehicleRepository.findByIdAndIsDeletedFalse(id)
                .map(vehicle -> {
                    vehicle.setStatus(Vehicle.VehicleStatus.PARKED_OUTSIDE);
                    vehicle.setCurrentParkingSpaceId(null);
                    vehicle.setExitTime(LocalDateTime.now());
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle reserveSpace(Long id, String parkingSpaceId) {
        return vehicleRepository.findByIdAndIsDeletedFalse(id)
                .map(vehicle -> {
                    vehicle.setStatus(Vehicle.VehicleStatus.RESERVED_SPACE);
                    vehicle.setCurrentParkingSpaceId(parkingSpaceId);
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    // Soft delete - marks vehicle as deleted
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        vehicle.markAsDeleted();
        vehicleRepository.save(vehicle);
    }

    // Hard delete - permanently removes from database
    public void hardDeleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    // Restore deleted vehicle
    public Vehicle restoreVehicle(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    if (!vehicle.getIsDeleted()) {
                        throw new RuntimeException("Vehicle is not deleted");
                    }
                    vehicle.restore();
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Optional<Vehicle> findVehicleInParkingSpace(String parkingSpaceId) {
        return vehicleRepository.findByCurrentParkingSpaceIdAndIsDeletedFalse(parkingSpaceId);
    }

    public List<Vehicle> getParkedVehiclesByOwner(String ownerId) {
        List<Vehicle.VehicleStatus> parkedStatuses = List.of(
                Vehicle.VehicleStatus.PARKED_IN_SPACE,
                Vehicle.VehicleStatus.RESERVED_SPACE
        );
        return vehicleRepository.findByOwnerIdAndStatusInAndIsDeletedFalse(ownerId, parkedStatuses);
    }

    public Long getParkedVehicleCount(String ownerId) {
        return vehicleRepository.countByOwnerIdAndStatusAndIsDeletedFalse(ownerId, Vehicle.VehicleStatus.PARKED_IN_SPACE);
    }
}