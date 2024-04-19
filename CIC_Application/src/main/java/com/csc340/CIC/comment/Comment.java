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

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "bill_id", nullable = false)
    private String billId;

    @Column(name = "comment_date", nullable = false)
    private Date commentDate;

    public Comment(){

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n\tcommentId: ").append(commentId).append("\n\t");
        sb.append("billId: ").append(billId).append("\n\t");
        sb.append("userId: ").append(userId).append("\n\t");
        sb.append("commentText: ").append(commentText).append("\n\t");
        sb.append("commentDate: ").append(commentDate).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
