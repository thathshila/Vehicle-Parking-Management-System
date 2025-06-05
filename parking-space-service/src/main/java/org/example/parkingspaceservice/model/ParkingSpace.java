package org.example.parkingspaceservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String spaceNumber;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String zone;

    @Column(nullable = false)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpaceStatus status;

    @Column(nullable = false)
    private Double hourlyRate;

    private String ownerId;

    private String reservedBy;

    private LocalDateTime reservationTime;

    private LocalDateTime lastUpdated;

    // Constructors
    public ParkingSpace() {
        this.lastUpdated = LocalDateTime.now();
    }

    public ParkingSpace(String spaceNumber, String location, String zone, String city,
                        Double hourlyRate, String ownerId) {
        this.spaceNumber = spaceNumber;
        this.location = location;
        this.zone = zone;
        this.city = city;
        this.hourlyRate = hourlyRate;
        this.ownerId = ownerId;
        this.status = SpaceStatus.AVAILABLE;
        this.lastUpdated = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSpaceNumber() { return spaceNumber; }
    public void setSpaceNumber(String spaceNumber) { this.spaceNumber = spaceNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public SpaceStatus getStatus() { return status; }
    public void setStatus(SpaceStatus status) {
        this.status = status;
        this.lastUpdated = LocalDateTime.now();
    }

    public Double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(Double hourlyRate) { this.hourlyRate = hourlyRate; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getReservedBy() { return reservedBy; }
    public void setReservedBy(String reservedBy) { this.reservedBy = reservedBy; }

    public LocalDateTime getReservationTime() { return reservationTime; }
    public void setReservationTime(LocalDateTime reservationTime) { this.reservationTime = reservationTime; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
