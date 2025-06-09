
package org.example.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.DRIVER;

    private String address;
    private String city;
    private String state;
    private String zipCode;

    private boolean active = true;

    // Add status field for soft delete management
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private LocalDateTime lastLoginTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingHistory> bookingHistory = new ArrayList<>();

    public enum UserRole {
        DRIVER, PARKING_OWNER, ADMIN
    }

    // Add UserStatus enum for better status management
    public enum UserStatus {
        ACTIVE,      // User is active and can use the system
        INACTIVE,    // User is temporarily deactivated
        DELETED,     // User is soft deleted
        SUSPENDED    // User is suspended (optional for future use)
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

    // Helper methods for status management
    public boolean isDeleted() {
        return this.status == UserStatus.DELETED;
    }

    public boolean isActiveStatus() {
        return this.status == UserStatus.ACTIVE && this.active;
    }

    public void softDelete() {
        this.status = UserStatus.DELETED;
        this.active = false;
        this.deletedAt = LocalDateTime.now();
    }

    public void restore() {
        this.status = UserStatus.ACTIVE;
        this.active = true;
        this.deletedAt = null;
    }
}