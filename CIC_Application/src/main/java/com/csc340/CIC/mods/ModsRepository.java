package com.csc340.CIC.mods;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModsRepository extends JpaRepository<Report, Long> {
;
}