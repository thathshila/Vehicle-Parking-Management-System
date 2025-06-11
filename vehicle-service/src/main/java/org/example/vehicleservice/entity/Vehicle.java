package org.example.vehicleservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "License plate is required")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Invalid license plate format")
    @Column(unique = true)
    private String licensePlate;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    private String color;

    private Integer year;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @NotBlank(message = "Owner ID is required")
    private String ownerId;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status = VehicleStatus.PARKED_OUTSIDE;

    private String currentParkingSpaceId;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum VehicleType {
        CAR, MOTORCYCLE, TRUCK, VAN, SUV, ELECTRIC_CAR
    }

    public enum VehicleStatus {
        PARKED_OUTSIDE, PARKED_IN_SPACE, IN_TRANSIT, RESERVED_SPACE
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