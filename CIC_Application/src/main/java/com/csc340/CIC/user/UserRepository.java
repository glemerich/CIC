package com.csc340.CIC.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find a User by username.
    Optional <User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.approvalStatus = 'pending'")
    List<User> findByApprovalStatusPending();

    List<User> findByStatusTrue();

    List<User> findByReportedStatusTrue();

    // Method to delete a User by their username.
    @Modifying
    @Transactional
    void deleteByUsername(String username);
}
