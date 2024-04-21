package com.csc340.CIC.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReportService {

    @Autowired
    private UserReportRepository userReportRepository;

    public List<UserReport> getAllReports() {
        return userReportRepository.findAll();
    }

    // Additional methods to handle ban, ignore, and tempBan actions
    public void banUser(Long id) {
        // Implement your ban logic
    }

    public void tempBanUser(Long id) {
        // Implement your temp ban logic
    }

    public void ignoreReport(Long id) {
        // Implement your ignore logic
    }
}
