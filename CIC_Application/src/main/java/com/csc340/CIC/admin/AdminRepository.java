package com.csc340.CIC.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByStatus(String status);
}
