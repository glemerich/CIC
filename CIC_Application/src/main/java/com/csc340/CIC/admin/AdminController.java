package com.csc340.CIC.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class AdminController {
    
    @Autowired
    private final AdminService service;

    
    public AdminController(AdminService service) {
        this.service = service;
    }

    // Fetches all active reports and displays them on the admin page
    @GetMapping("/admin/all")
    public String showReports(Model model) {
        List<Admin> reports = service.findActiveReports();
        model.addAttribute("reports", reports);
        return "admin/admin";
    }

    // Bans a user 
    @GetMapping("/admin/ban/{reportId}")
    public String banReport(@PathVariable Long reportId) {
        service.banReport(reportId);
        return "redirect:/admin/all";
    }

    // Ignores a report 
    @GetMapping("/admin/ignore/{reportId}")
    public String ignoreReport(@PathVariable Long reportId) {
        service.ignoreReport(reportId);
        return "redirect:/admin/all";
    }
}
