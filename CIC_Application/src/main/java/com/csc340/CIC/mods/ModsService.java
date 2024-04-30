package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.comment.CommentService;
import com.csc340.CIC.user.User;
import com.csc340.CIC.user.UserNotFoundException;
import com.csc340.CIC.user.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ModsService {
    
    private final ModsRepository modsRepository;
    private final CommentService commentService; // Inject CommentService
    private final UserRepository userRepository; // Inject UserRepository

    @Autowired
    public ModsService(ModsRepository modsRepository, CommentService commentService, UserRepository userRepository) {
        this.modsRepository = modsRepository;
        this.commentService = commentService; // Initialize CommentService
        this.userRepository = userRepository; // Initialize UserRepository
        
    }

    // Method to get reported comments
    public List<Comment> getReportedComments() {
        return modsRepository.findByCommentReportTrue();
    }

    // Method to delete reported comment by ID
    public void deleteByID(long commentID) {
        modsRepository.deleteById(commentID);
    }

    public void ignoreReportedComment(Long commentId) {
        // Fetch the comment by its ID
        Comment comment = commentService.getCommentById(commentId);
        // Set the report status to false
        comment.setCommentReport(false);
        // Update the comment in the database
        commentService.updateComment(comment);
    }

    public void reportUser(Long userId) {
        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Set the user's reported flag to true
            user.setUserReported(true);
            
            // Save the updated user
            userRepository.save(user);
        } else {
            // Handle the case where the user does not exist
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }


}
