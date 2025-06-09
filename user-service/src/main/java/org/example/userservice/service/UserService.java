//package org.example.userservice.service;
//
//import org.example.userservice.model.User;
//import org.example.userservice.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//@Transactional
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public Optional<User> getUserById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    public Optional<User> getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    public Optional<User> getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    public List<User> getUsersByRole(User.UserRole role) {
//        return userRepository.findByRole(role);
//    }
//
//    public List<User> getUsersByCity(String city) {
//        return userRepository.findByCity(city);
//    }
//
//    public List<User> getActiveUsers() {
//        return userRepository.findByActive(true);
//    }
//
//    public User createUser(User user) {
//        if (userRepository.existsByUsername(user.getUsername())) {
//            throw new RuntimeException("Username already exists");
//        }
//        if (userRepository.existsByEmail(user.getEmail())) {
//            throw new RuntimeException("Email already exists");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public User updateUser(Long id, User userDetails) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setFirstName(userDetails.getFirstName());
//                    user.setLastName(userDetails.getLastName());
//                    user.setPhoneNumber(userDetails.getPhoneNumber());
//                    user.setAddress(userDetails.getAddress());
//                    user.setCity(userDetails.getCity());
//                    user.setState(userDetails.getState());
//                    user.setZipCode(userDetails.getZipCode());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//    }
//
//    public User authenticateUser(String username, String password) {
//        Optional<User> userOpt = userRepository.findByUsername(username);
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            if (passwordEncoder.matches(password, user.getPassword()) && user.isActive()) {
//                user.setLastLoginTime(LocalDateTime.now());
//                return userRepository.save(user);
//            }
//        }
//        throw new RuntimeException("Invalid credentials");
//    }
//
//    public User changePassword(Long id, String oldPassword, String newPassword) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
//                        throw new RuntimeException("Invalid old password");
//                    }
//                    user.setPassword(passwordEncoder.encode(newPassword));
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//    }
//
//    public User deactivateUser(Long id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setActive(false);
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//    }
//
//    public User activateUser(Long id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setActive(true);
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    public List<User> searchUsersByName(String name) {
//        return userRepository.findByNameContaining(name);
//    }
//
//    public Long getActiveUserCountByRole(User.UserRole role) {
//        return userRepository.countActiveUsersByRole(role);
//    }
//}
package org.example.userservice.service;

import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Get all active users (excluding deleted ones)
    public List<User> getAllUsers() {
        return userRepository.findAllActive();
    }

    // Get all users including deleted ones (for admin purposes)
    public List<User> getAllUsersIncludingDeleted() {
        return userRepository.findAll();
    }

    // Get deleted users only
    public List<User> getDeletedUsers() {
        return userRepository.findAllDeleted();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findActiveById(id);
    }

    // Get user by ID including deleted (for admin purposes)
    public Optional<User> getUserByIdIncludingDeleted(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByRole(User.UserRole role) {
        return userRepository.findByRole(role);
    }

    public List<User> getUsersByCity(String city) {
        return userRepository.findByCity(city);
    }

    public List<User> getActiveUsers() {
        return userRepository.findByActive(true);
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(User.UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findActiveById(id)
                .map(user -> {
                    // Check if trying to update deleted user
                    if (user.isDeleted()) {
                        throw new RuntimeException("Cannot update deleted user");
                    }

                    user.setFirstName(userDetails.getFirstName());
                    user.setLastName(userDetails.getLastName());
                    user.setPhoneNumber(userDetails.getPhoneNumber());
                    user.setAddress(userDetails.getAddress());
                    user.setCity(userDetails.getCity());
                    user.setState(userDetails.getState());
                    user.setZipCode(userDetails.getZipCode());

                    // Update status if provided
                    if (userDetails.getStatus() != null && userDetails.getStatus() != User.UserStatus.DELETED) {
                        user.setStatus(userDetails.getStatus());
                    }

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User authenticateUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword()) &&
                    user.isActiveStatus() && !user.isDeleted()) {
                user.setLastLoginTime(LocalDateTime.now());
                return userRepository.save(user);
            }
        }
        throw new RuntimeException("Invalid credentials or user is not active");
    }

    public User changePassword(Long id, String oldPassword, String newPassword) {
        return userRepository.findActiveById(id)
                .map(user -> {
                    if (user.isDeleted()) {
                        throw new RuntimeException("Cannot change password for deleted user");
                    }
                    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                        throw new RuntimeException("Invalid old password");
                    }
                    user.setPassword(passwordEncoder.encode(newPassword));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User deactivateUser(Long id) {
        return userRepository.findActiveById(id)
                .map(user -> {
                    if (user.isDeleted()) {
                        throw new RuntimeException("Cannot deactivate deleted user");
                    }
                    user.setActive(false);
                    user.setStatus(User.UserStatus.INACTIVE);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User activateUser(Long id) {
        return userRepository.findActiveById(id)
                .map(user -> {
                    if (user.isDeleted()) {
                        throw new RuntimeException("Cannot activate deleted user. Use restore instead.");
                    }
                    user.setActive(true);
                    user.setStatus(User.UserStatus.ACTIVE);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Soft delete user
    public User deleteUser(Long id) {
        return userRepository.findActiveById(id)
                .map(user -> {
                    user.softDelete();
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Restore deleted user
    public User restoreUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    if (!user.isDeleted()) {
                        throw new RuntimeException("User is not deleted");
                    }
                    user.restore();
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Permanently delete user (hard delete)
    public void permanentlyDeleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (!user.get().isDeleted()) {
                throw new RuntimeException("User must be soft deleted before permanent deletion");
            }
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    public Long getActiveUserCountByRole(User.UserRole role) {
        return userRepository.countActiveUsersByRole(role);
    }

    // New methods for status management
    public List<User> getUsersByStatus(User.UserStatus status) {
        return userRepository.findByStatus(status);
    }

    public List<User> getUsersByStatusAndRole(User.UserStatus status, User.UserRole role) {
        return userRepository.findByStatusAndRole(status, role);
    }

    public Long getTotalActiveUsers() {
        return userRepository.countActiveUsers();
    }

    public Long getTotalDeletedUsers() {
        return userRepository.countDeletedUsers();
    }

    public Long getUserCountByStatus(User.UserStatus status) {
        return userRepository.countByStatus(status);
    }
}