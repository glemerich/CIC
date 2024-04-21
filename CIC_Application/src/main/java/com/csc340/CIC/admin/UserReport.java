package com.csc340.CIC.admin;

import javax.persistence.*;

@Entity
@Table(name = "user_reports")  // This should match the actual table name in the database
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reported_user")
    private String reportedUser;

    @Column(name = "reported_by")
    private String reportedBy;

    @Column(name = "reason_reported")
    private String reasonReported;

    @Column(name = "details")
    private String details;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
