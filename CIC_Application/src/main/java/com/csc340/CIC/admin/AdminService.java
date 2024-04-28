package com.csc340.CIC.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.csc340.CIC.user.UserRepository;

@Service
public class AdminService {

    private final AdminRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // Fetches active reports
    public List<Admin> findActiveReports() {
        return repository.findByStatus("ACTIVE");
    }

    // Bans a user based on report ID
    public void banReport(Long reportId) {
        Admin report = repository.findById(reportId).orElseThrow();
        userRepository.deleteByUsername(report.getReportedUser());
        report.setStatus("BANNED");
        repository.save(report);
    }

    // Ignores a report
    public void ignoreReport(Long reportId) {
        Admin report = repository.findById(reportId).orElseThrow();
        report.setStatus("IGNORED");
        repository.save(report);
    }
}
