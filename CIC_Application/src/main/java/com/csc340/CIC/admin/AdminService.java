package com.csc340.CIC.admin;


import com.csc340.CIC.user.User;
import com.csc340.CIC.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private final UserService userService;

    
    public AdminService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsersWithPendingApproval() {
        return userService.getPendingUsers();
    }

    public List<User> getAllUsersWithReportedStatusTrue() {
        return userService.getReportedUsers();
    }
}
