package org.example.vehicleservice.controller;

import jakarta.validation.Valid;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/license/{licensePlate}")
    public ResponseEntity<Vehicle> getVehicleByLicensePlate(@PathVariable String licensePlate) {
        return vehicleService.getVehicleByLicensePlate(licensePlate)
                .map(vehicle -> ResponseEntity.ok(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByOwner(@PathVariable String ownerId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByOwner(ownerId);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Vehicle>> getVehiclesByStatus(@PathVariable Vehicle.VehicleStatus status) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByStatus(status);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Vehicle>> getVehiclesByType(@PathVariable Vehicle.VehicleType type) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByType(type);
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
        try {
            Vehicle createdVehicle = vehicleService.createVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id,
                                                 @Valid @RequestBody Vehicle vehicleDetails) {
        try {
            Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);
            return ResponseEntity.ok(updatedVehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/entry")
    public ResponseEntity<Vehicle> simulateEntry(@PathVariable Long id,
                                                 @RequestBody Map<String, String> request) {
        try {
            String parkingSpaceId = request.get("parkingSpaceId");
            Vehicle vehicle = vehicleService.simulateEntry(id, parkingSpaceId);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<Vehicle> simulateExit(@PathVariable Long id) {
        try {
            Vehicle vehicle = vehicleService.simulateExit(id);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<Vehicle> reserveSpace(@PathVariable Long id,
                                                @RequestBody Map<String, String> request) {
        try {
            String parkingSpaceId = request.get("parkingSpaceId");
            Vehicle vehicle = vehicleService.reserveSpace(id, parkingSpaceId);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/parking-space/{parkingSpaceId}")
    public ResponseEntity<Vehicle> findVehicleInParkingSpace(@PathVariable String parkingSpaceId) {
        return vehicleService.findVehicleInParkingSpace(parkingSpaceId)
                .map(vehicle -> ResponseEntity.ok(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/owner/{ownerId}/parked")
    public ResponseEntity<List<Vehicle>> getParkedVehiclesByOwner(@PathVariable String ownerId) {
        List<Vehicle> vehicles = vehicleService.getParkedVehiclesByOwner(ownerId);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/owner/{ownerId}/stats")
    public ResponseEntity<Map<String, Long>> getOwnerVehicleStats(@PathVariable String ownerId) {
        Long parkedCount = vehicleService.getParkedVehicleCount(ownerId);
        Long totalCount = (long) vehicleService.getVehiclesByOwner(ownerId).size();

        Map<String, Long> stats = Map.of(
                "parked", parkedCount,
                "total", totalCount,
                "available", totalCount - parkedCount
        );

        return ResponseEntity.ok(stats);
    }
}
