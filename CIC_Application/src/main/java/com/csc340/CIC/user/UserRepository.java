package com.csc340.CIC.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

// This interface extends JpaRepository, providing CRUD operations and query capabilities for User entities.
public interface UserRepository extends JpaRepository<User, Long> {

    // Declare a method to find a User by username. This method uses Spring Data's query derivation mechanism.
    // The method returns an Optional<User>, which will contain the User if found, or be empty if not found.
    Optional<User> findByUsername(String username);

    // Method to delete a User by their username.
    // @Modifying indicates that this method will be modified query (e.g., INSERT, UPDATE, DELETE).
    @Modifying
    
    @Transactional
    void deleteByUsername(String username);
}
