package org.example.parkingspaceservice.api;

import org.example.parkingspaceservice.model.ParkingSpace;
import org.example.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin(origins = "*")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @GetMapping("/spaces")
    public ResponseEntity<List<ParkingSpace>> getAllParkingSpaces() {
        List<ParkingSpace> spaces = parkingSpaceService.getAllParkingSpaces();
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/spaces/{id}")
    public ResponseEntity<ParkingSpace> getParkingSpaceById(@PathVariable Long id) {
        return parkingSpaceService.getParkingSpaceById(id)
                .map(space -> ResponseEntity.ok(space))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/spaces/number/{spaceNumber}")
    public ResponseEntity<ParkingSpace> getParkingSpaceByNumber(@PathVariable String spaceNumber) {
        return parkingSpaceService.getParkingSpaceByNumber(spaceNumber)
                .map(space -> ResponseEntity.ok(space))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/spaces/available")
    public ResponseEntity<List<ParkingSpace>> getAvailableSpaces() {
        List<ParkingSpace> spaces = parkingSpaceService.getAvailableSpaces();
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/spaces/city/{city}")
    public ResponseEntity<List<ParkingSpace>> getSpacesByCity(@PathVariable String city) {
        List<ParkingSpace> spaces = parkingSpaceService.getSpacesByCity(city);
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/spaces/zone/{zone}")
    public ResponseEntity<List<ParkingSpace>> getSpacesByZone(@PathVariable String zone) {
        List<ParkingSpace> spaces = parkingSpaceService.getSpacesByZone(zone);
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/spaces/owner/{ownerId}")
    public ResponseEntity<List<ParkingSpace>> getSpacesByOwner(@PathVariable String ownerId) {
        List<ParkingSpace> spaces = parkingSpaceService.getSpacesByOwner(ownerId);
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/spaces/available/city/{city}")
    public ResponseEntity<List<ParkingSpace>> getAvailableSpacesByCity(@PathVariable String city) {
        List<ParkingSpace> spaces = parkingSpaceService.getAvailableSpacesByCity(city);
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/spaces/available/zone/{zone}")
    public ResponseEntity<List<ParkingSpace>> getAvailableSpacesByZone(@PathVariable String zone) {
        List<ParkingSpace> spaces = parkingSpaceService.getAvailableSpacesByZone(zone);
        return ResponseEntity.ok(spaces);
    }

    @PostMapping("/spaces")
    public ResponseEntity<ParkingSpace> createParkingSpace(@Valid @RequestBody ParkingSpace parkingSpace) {
        try {
            ParkingSpace createdSpace = parkingSpaceService.createParkingSpace(parkingSpace);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSpace);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/spaces/{id}")
    public ResponseEntity<ParkingSpace> updateParkingSpace(@PathVariable Long id,
                                                           @Valid @RequestBody ParkingSpace parkingSpaceDetails) {
        try {
            ParkingSpace updatedSpace = parkingSpaceService.updateParkingSpace(id, parkingSpaceDetails);
            return ResponseEntity.ok(updatedSpace);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/spaces/{id}/reserve")
    public ResponseEntity<ParkingSpace> reserveSpace(@PathVariable Long id,
                                                     @RequestBody Map<String, String> request) {
        try {
            String vehicleId = request.get("vehicleId");
            ParkingSpace reservedSpace = parkingSpaceService.reserveSpace(id, vehicleId);
            return ResponseEntity.ok(reservedSpace);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/spaces/{id}/occupy")
    public ResponseEntity<ParkingSpace> occupySpace(@PathVariable Long id,
                                                    @RequestBody Map<String, String> request) {
        try {
            String vehicleId = request.get("vehicleId");
            ParkingSpace occupiedSpace = parkingSpaceService.occupySpace(id, vehicleId);
            return ResponseEntity.ok(occupiedSpace);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/spaces/{id}/release")
    public ResponseEntity<ParkingSpace> releaseSpace(@PathVariable Long id) {
        try {
            ParkingSpace releasedSpace = parkingSpaceService.releaseSpace(id);
            return ResponseEntity.ok(releasedSpace);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/spaces/{id}")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable Long id) {
        try {
            parkingSpaceService.deleteParkingSpace(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/spaces/stats/city/{city}")
    public ResponseEntity<Map<String, Long>> getCityStats(@PathVariable String city) {
        Long availableCount = parkingSpaceService.getAvailableSpaceCount(city);
        Long occupiedCount = parkingSpaceService.getOccupiedSpaceCount(city);

        Map<String, Long> stats = Map.of(
                "available", availableCount,
                "occupied", occupiedCount,
                "total", availableCount + occupiedCount
        );

        return ResponseEntity.ok(stats);
    }

    @GetMapping("/spaces/vehicle/{vehicleId}")
    public ResponseEntity<ParkingSpace> findSpaceByVehicle(@PathVariable String vehicleId) {
        return parkingSpaceService.findSpaceByVehicle(vehicleId)
                .map(space -> ResponseEntity.ok(space))
                .orElse(ResponseEntity.notFound().build());
    }
}
