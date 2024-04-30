package com.csc340.CIC.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.CIC.user.User;
import com.csc340.CIC.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private final AdminService service;

    @Autowired
    private final UserService userService;

    
    public AdminController(AdminService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    // Fetches all active reports and displays them on the admin page
    @GetMapping("/all")
    public String displayAdmin(Model model) {
        List<User> reportedUsers = service.getAllUsersWithReportedStatusTrue();
        model.addAttribute("reportedUsers", reportedUsers);

        List<User> pendingUsers = service.getAllUsersWithPendingApproval();
        model.addAttribute("pendingUsers", pendingUsers);
        return "admin/admin";
    }

    @GetMapping("/ban/{username}")
    public String banReportedUser(@RequestParam("username") String username, Model model ){
        User user = userService.getUserByUsername(username);
        user.setStatus(false);
        return "redirect:/admin/all";
    }

    @GetMapping("/ignore/{username}")
    public String ignoreReportedUser(@RequestParam("username") String username, Model model ){
        User user = userService.getUserByUsername(username);
        user.setReportedStatus(false);
        return "redirect:/admin/all";
    }

}
