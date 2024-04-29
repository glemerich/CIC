package com.csc340.CIC.mods;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Mods")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "comment_text", length = 255)
    private String commentText;

    
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "reported_id", nullable = false)
    private Long reportedId;

    @Column(name = "reported_text", length = 255)
    private String reportedText;




    public Report(){

    }




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




    public Long getUserId() {
        return userId;
    }




    public void setUserId(Long userId) {
        this.userId = userId;
    }




    public Long getReportedId() {
        return reportedId;
    }




    public void setReportedId(Long reportedId) {
        this.reportedId = reportedId;
    }




    public String getReportedText() {
        return reportedText;
    }




    public void setReportedText(String reportedText) {
        this.reportedText = reportedText;
    }

    

    
 

}
