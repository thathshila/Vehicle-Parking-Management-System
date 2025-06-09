//package org.example.userservice.api;
//
//import jakarta.validation.Valid;
//import org.example.userservice.model.User;
//import org.example.userservice.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/users")
//@CrossOrigin(origins = "*")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(user -> ResponseEntity.ok(user))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/username/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//        return userService.getUserByUsername(username)
//                .map(user -> ResponseEntity.ok(user))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/email/{email}")
//    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
//        return userService.getUserByEmail(email)
//                .map(user -> ResponseEntity.ok(user))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/role/{role}")
//    public ResponseEntity<List<User>> getUsersByRole(@PathVariable User.UserRole role) {
//        List<User> users = userService.getUsersByRole(role);
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/city/{city}")
//    public ResponseEntity<List<User>> getUsersByCity(@PathVariable String city) {
//        List<User> users = userService.getUsersByCity(city);
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/active")
//    public ResponseEntity<List<User>> getActiveUsers() {
//        List<User> users = userService.getActiveUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @PostMapping
//    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//        try {
//            User createdUser = userService.createUser(user);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
//        try {
//            User updatedUser = userService.updateUser(id, userDetails);
//            return ResponseEntity.ok(updatedUser);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<User> authenticateUser(@RequestBody Map<String, String> credentials) {
//        try {
//            String username = credentials.get("username");
//            String password = credentials.get("password");
//            User authenticatedUser = userService.authenticateUser(username, password);
//            return ResponseEntity.ok(authenticatedUser);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @PostMapping("/{id}/change-password")
//    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody Map<String, String> passwords) {
//        try {
//            String oldPassword = passwords.get("oldPassword");
//            String newPassword = passwords.get("newPassword");
//            User user = userService.changePassword(id, oldPassword, newPassword);
//            return ResponseEntity.ok(user);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @PostMapping("/{id}/deactivate")
//    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
//        try {
//            User user = userService.deactivateUser(id);
//            return ResponseEntity.ok(user);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/{id}/activate")
//    public ResponseEntity<User> activateUser(@PathVariable Long id) {
//        try {
//            User user = userService.activateUser(id);
//            return ResponseEntity.ok(user);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        try {
//            userService.deleteUser(id);
//            return ResponseEntity.noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<User>> searchUsersByName(@RequestParam String name) {
//        List<User> users = userService.searchUsersByName(name);
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/stats/role/{role}")
//    public ResponseEntity<Map<String, Long>> getUserStatsByRole(@PathVariable User.UserRole role) {
//        Long activeCount = userService.getActiveUserCountByRole(role);
//        Map<String, Long> stats = Map.of("activeUsers", activeCount);
//        return ResponseEntity.ok(stats);
//    }
//}
package org.example.userservice.api;

import jakarta.validation.Valid;
import org.example.userservice.model.User;
import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all active users (default behavior)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get all users including deleted ones (admin only)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsersIncludingDeleted() {
        List<User> users = userService.getAllUsersIncludingDeleted();
        return ResponseEntity.ok(users);
    }

    // Get deleted users only
    @GetMapping("/deleted")
    public ResponseEntity<List<User>> getDeletedUsers() {
        List<User> users = userService.getDeletedUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by ID including deleted (admin only)
    @GetMapping("/{id}/full")
    public ResponseEntity<User> getUserByIdIncludingDeleted(@PathVariable Long id) {
        return userService.getUserByIdIncludingDeleted(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable User.UserRole role) {
        List<User> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<User>> getUsersByCity(@PathVariable String city) {
        List<User> users = userService.getUsersByCity(city);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> users = userService.getActiveUsers();
        return ResponseEntity.ok(users);
    }

    // New endpoint for status filtering
    @GetMapping("/status/{status}")
    public ResponseEntity<List<User>> getUsersByStatus(@PathVariable User.UserStatus status) {
        List<User> users = userService.getUsersByStatus(status);
        return ResponseEntity.ok(users);
    }

    // Get users by status and role
    @GetMapping("/status/{status}/role/{role}")
    public ResponseEntity<List<User>> getUsersByStatusAndRole(
            @PathVariable User.UserStatus status,
            @PathVariable User.UserRole role) {
        List<User> users = userService.getUsersByStatusAndRole(status, role);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");
            User authenticatedUser = userService.authenticateUser(username, password);
            return ResponseEntity.ok(authenticatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody Map<String, String> passwords) {
        try {
            String oldPassword = passwords.get("oldPassword");
            String newPassword = passwords.get("newPassword");
            User user = userService.changePassword(id, oldPassword, newPassword);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        try {
            User user = userService.deactivateUser(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<User> activateUser(@PathVariable Long id) {
        try {
            User user = userService.activateUser(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Soft delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        try {
            User deletedUser = userService.deleteUser(id);
            Map<String, String> response = Map.of(
                    "message", "User soft deleted successfully",
                    "userId", id.toString(),
                    "status", deletedUser.getStatus().toString()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Restore deleted user
    @PostMapping("/{id}/restore")
    public ResponseEntity<User> restoreUser(@PathVariable Long id) {
        try {
            User restoredUser = userService.restoreUser(id);
            return ResponseEntity.ok(restoredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Permanently delete user (admin only)
    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Map<String, String>> permanentlyDeleteUser(@PathVariable Long id) {
        try {
            userService.permanentlyDeleteUser(id);
            Map<String, String> response = Map.of(
                    "message", "User permanently deleted",
                    "userId", id.toString()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByName(@RequestParam String name) {
        List<User> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/stats/role/{role}")
    public ResponseEntity<Map<String, Long>> getUserStatsByRole(@PathVariable User.UserRole role) {
        Long activeCount = userService.getActiveUserCountByRole(role);
        Map<String, Long> stats = Map.of("activeUsers", activeCount);
        return ResponseEntity.ok(stats);
    }

    // New statistics endpoints
    @GetMapping("/stats/total")
    public ResponseEntity<Map<String, Long>> getTotalUserStats() {
        Long activeUsers = userService.getTotalActiveUsers();
        Long deletedUsers = userService.getTotalDeletedUsers();
        Long totalUsers = activeUsers + deletedUsers;

        Map<String, Long> stats = Map.of(
                "totalUsers", totalUsers,
                "activeUsers", activeUsers,
                "deletedUsers", deletedUsers
        );
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/status/{status}")
    public ResponseEntity<Map<String, Long>> getUserStatsByStatus(@PathVariable User.UserStatus status) {
        Long count = userService.getUserCountByStatus(status);
        Map<String, Long> stats = Map.of("count", count);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/status")
    public ResponseEntity<Map<String, Long>> getAllStatusStats() {
        Map<String, Long> stats = Map.of(
                "active", userService.getUserCountByStatus(User.UserStatus.ACTIVE),
                "inactive", userService.getUserCountByStatus(User.UserStatus.INACTIVE),
                "deleted", userService.getUserCountByStatus(User.UserStatus.DELETED),
                "suspended", userService.getUserCountByStatus(User.UserStatus.SUSPENDED)
        );
        return ResponseEntity.ok(stats);
    }
}