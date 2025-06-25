
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

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.status != 'DELETED'")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.status != 'DELETED'")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.status != 'DELETED'")
    Optional<User> findActiveById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.status != 'DELETED'")
    List<User> findByRole(@Param("role") User.UserRole role);

    @Query("SELECT u FROM User u WHERE u.city = :city AND u.status != 'DELETED'")
    List<User> findByCity(@Param("city") String city);

    @Query("SELECT u FROM User u WHERE u.active = :active AND u.status != 'DELETED'")
    List<User> findByActive(@Param("active") boolean active);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role AND u.active = true AND u.status != 'DELETED'")
    Long countActiveUsersByRole(@Param("role") User.UserRole role);

    @Query("SELECT u FROM User u WHERE (u.firstName LIKE %:name% OR u.lastName LIKE %:name%) AND u.status != 'DELETED'")
    List<User> findByNameContaining(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username AND u.status != 'DELETED'")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email AND u.status != 'DELETED'")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.status != 'DELETED'")
    List<User> findAllActive();

    @Query("SELECT u FROM User u WHERE u.status = 'DELETED'")
    List<User> findAllDeleted();

    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findByStatus(@Param("status") User.UserStatus status);

    @Query("SELECT u FROM User u WHERE u.status = :status AND u.role = :role")
    List<User> findByStatusAndRole(@Param("status") User.UserStatus status, @Param("role") User.UserRole role);

    @Query("SELECT COUNT(u) FROM User u WHERE u.status != 'DELETED'")
    Long countActiveUsers();

    @Query("SELECT COUNT(u) FROM User u WHERE u.status = 'DELETED'")
    Long countDeletedUsers();

    @Query("SELECT COUNT(u) FROM User u WHERE u.status = :status")
    Long countByStatus(@Param("status") User.UserStatus status);
}