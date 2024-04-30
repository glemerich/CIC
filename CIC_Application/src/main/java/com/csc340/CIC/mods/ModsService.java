package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.comment.CommentService;
import com.csc340.CIC.user.User;
import com.csc340.CIC.user.UserService;

import java.util.List;

@Service
public class ModsService {
    
    @Autowired
    private final CommentService commentService;

    @Autowired
    private final UserService userService;

    
    public ModsService(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    // Method to get reported comments
    public List<Comment> getReportedComments() {
        return commentService.findByReportedStatusTrue();
    }

    // Method to delete reported comment by ID
    public void deleteByID(long commentID) {
        commentService.deleteById(commentID);
    }

    
    public void ignoreReportedComment(Long commentId) {
        // Fetch the comment by its ID
        Comment comment = commentService.getCommentById(commentId);
        // Set the report status to false
        comment.setReportedStatus(false);
        // Update the comment in the database
        commentService.updateComment(comment);
    }
    
    public void reportUser(String username) {
        // Check if the user exists
        User user = userService.getUserByUsername(username);
        if (user != null) {
            
            user.setReportedStatus(true);
            
            // Save the updated user
            userService.save(user);
        } else {
            return;
        }
    }
    
}
