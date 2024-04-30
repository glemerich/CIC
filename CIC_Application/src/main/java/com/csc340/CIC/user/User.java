package com.csc340.CIC.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String role;

    private Date createdAt;

    private boolean status;

    private String requested_role;

    private String approval_status;
    
    private boolean reported_status;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRequestedRole() {
        return requested_role;
    }

    public void setRequestedRole(String requestedRole) {
        this.requested_role = requestedRole;
    }

    public String getApprovalStatus() {
        return approval_status;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approval_status = approvalStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRequested_role() {
        return requested_role;
    }

    public void setRequested_role(String requested_role) {
        this.requested_role = requested_role;
    }

    public String getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(String approval_status) {
        this.approval_status = approval_status;
    }

    public boolean isReported_status() {
        return reported_status;
    }

    public void setReported_status(boolean reported_status) {
        this.reported_status = reported_status;
    }
}

