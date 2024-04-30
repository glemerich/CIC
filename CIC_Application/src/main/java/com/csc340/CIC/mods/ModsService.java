package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.comment.CommentRepository;
import com.csc340.CIC.comment.CommentService;

import java.util.List;

@Service
public class ModsService {
    
    @Autowired
    private final CommentRepository commentRepository;

    
    public ModsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Method to get reported comments
    public List<Comment> getReportedComments() {
        return commentRepository.findByReportedStatusTrue();
    }

    // Method to delete reported comment by ID
    public void deleteByID(long commentID) {
        commentRepository.deleteById(commentID);
    }

    public void ignoreReportedComment(Long commentId) {
        // Fetch the comment by its ID
        Comment comment = CommentService.getCommentById(commentId);
        // Set the report status to false
        comment.setReportedStatus(false);
        // Update the comment in the database
        CommentService.updateComment(comment);
    }
    
    
}
