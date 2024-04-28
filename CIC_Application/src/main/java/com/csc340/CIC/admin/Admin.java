package com.csc340.CIC.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
// Maps the "user_reports" table from the database
@Table(name = "user_reports")
public class Admin {

    @Id
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId; 

    @Column(name = "reported_user")
    private String reportedUser; 

    @Column(name = "reported_by")
    private String reportedBy;

    @Column(name = "reason_reported")
    private String reasonReported;

    // Stores the status of the report, with a default value of ACTIVE
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVE'")
    private String status;  // New field for report status

    // Getters and setters for accessing and modifying 
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(String reportedUser) {
        this.reportedUser = reportedUser;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getReasonReported() {
        return reasonReported;
    }

    public void setReasonReported(String reasonReported) {
        this.reasonReported = reasonReported;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
