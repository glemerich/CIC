package com.csc340.CIC.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "bill_id", nullable = false)
    private String billId;

    @Column(name = "comment_date", nullable = false)
    private Date commentDate;

    @Column(name = "reported_status")
    private boolean reportedStatus;

    @Column(name = "comment_liked")
    private boolean liked;

    public Comment() {
        // Default constructor
    }

    // Getters and setters for all fields

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }


    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public boolean getReportedStatus() {
        return reportedStatus;
    }

    public void setReportedStatus(boolean reportedStatus){
        this.reportedStatus = reportedStatus;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n\tcommentId: ").append(commentId).append("\n\t");
        sb.append("billId: ").append(billId).append("\n\t");
        sb.append("username: ").append(username).append("\n\t");
        sb.append("commentText: ").append(commentText).append("\n\t");
        sb.append("commentDate: ").append(commentDate).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
