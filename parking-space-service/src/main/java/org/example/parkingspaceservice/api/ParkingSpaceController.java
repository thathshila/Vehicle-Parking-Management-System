package org.example.parkingspaceservice.api;

import org.example.parkingspaceservice.model.ParkingSpace;
import org.example.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/parkingSpace")
public class ParkingSpaceController {
    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @GetMapping("/spaces")
    public ResponseEntity<List<ParkingSpace>> getAllSpaces() {
        List<ParkingSpace> spaces = parkingSpaceService.getAllParkingSpaces();
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/spaces/{id}")
    public ResponseEntity<ParkingSpace> getSpaceById(@PathVariable Long id) {
        return parkingSpaceService.getParkingSpaceById(id)
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

    @GetMapping("/spaces/search")
    public ResponseEntity<List<ParkingSpace>> searchAvailableSpaces(
            @RequestParam String city,
            @RequestParam String zone) {
        List<ParkingSpace> spaces = parkingSpaceService.getAvailableSpacesByCityAndZone(city, zone);
        return ResponseEntity.ok(spaces);
    }

    @PostMapping("/spaces")
    public ResponseEntity<ParkingSpace> createSpace(@RequestBody ParkingSpace parkingSpace) {
        ParkingSpace createdSpace = parkingSpaceService.createParkingSpace(parkingSpace);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpace);
    }

    @PutMapping("/spaces/{id}")
    public ResponseEntity<ParkingSpace> updateSpace(@PathVariable Long id,
                                                    @RequestBody ParkingSpace parkingSpace) {
        return parkingSpaceService.updateParkingSpace(id, parkingSpace)
                .map(space -> ResponseEntity.ok(space))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/spaces/{id}/reserve")
    public ResponseEntity<ParkingSpace> reserveSpace(@PathVariable Long id,
                                                     @RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        return parkingSpaceService.reserveSpace(id, userId)
                .map(space -> ResponseEntity.ok(space))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/spaces/{id}/occupy")
    public ResponseEntity<ParkingSpace> occupySpace(@PathVariable Long id) {
        return parkingSpaceService.occupySpace(id)
                .map(space -> ResponseEntity.ok(space))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/spaces/{id}/release")
    public ResponseEntity<ParkingSpace> releaseSpace(@PathVariable Long id) {
        return parkingSpaceService.releaseSpace(id)
                .map(space -> ResponseEntity.ok(space))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/spaces/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        boolean deleted = parkingSpaceService.deleteParkingSpace(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
