package org.example.userservice.repository;


import org.example.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findByRole(User.UserRole role);

    List<User> findByCity(String city);

    List<User> findByActive(boolean active);

    List<User> findByRoleAndCity(User.UserRole role, String city);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role AND u.active = true")
    Long countActiveUsersByRole(@Param("role") User.UserRole role);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name% OR u.lastName LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
