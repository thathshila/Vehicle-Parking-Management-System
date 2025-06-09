package org.example.parkingspaceservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spaces")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Space number is required")
    @Column(unique = true)
    private String spaceNumber;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Zone is required")
    private String zone;

    @NotBlank(message = "City is required")
    private String city;

    @Enumerated(EnumType.STRING)
    private SpaceStatus status = SpaceStatus.AVAILABLE;

    @Enumerated(EnumType.STRING)
    private SpaceType type;

    @Positive(message = "Hourly rate must be positive")
    private Double hourlyRate;

    private String ownerId;

    private String currentVehicleId;

    private LocalDateTime occupiedSince;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum SpaceStatus {
        AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE
    }

    public enum SpaceType {
        REGULAR, COMPACT, LARGE, HANDICAPPED, ELECTRIC
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}