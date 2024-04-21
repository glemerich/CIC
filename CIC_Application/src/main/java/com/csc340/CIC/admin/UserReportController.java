package com.csc340.CIC.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserReportController {

    @Autowired
    private UserReportService userReportService;

    @GetMapping("/reported-users")
    public List<UserReport> getAllReportedUsers() {
        return userReportService.getAllReports();
    }

    @PostMapping("/action")
    public void takeActionOnUser(@RequestParam Long userId, @RequestParam String action) {
        switch (action.toLowerCase()) {
            case "ban":
                userReportService.banUser(userId);
                break;
            case "tempban":
                userReportService.tempBanUser(userId);
                break;
            case "ignore":
                userReportService.ignoreReport(userId);
                break;
        }
    }
}
