package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.comment.CommentRepository;

import java.util.List;

@Service
public class ModsService {
    
<<<<<<< HEAD
    private final ModsRepository modsRepository;
    private final CommentService commentService; // Inject CommentService

    @Autowired
    public ModsService(ModsRepository modsRepository, CommentService commentService) {
        this.modsRepository = modsRepository;
        this.commentService = commentService; // Initialize CommentService
=======
    @Autowired
    private final CommentRepository commentRepository;

    
    public ModsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
>>>>>>> 893196c1b4f4e1d5a5a538a6c2d518f46f0272fc
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
        Comment comment = commentService.getCommentById(commentId);
        // Set the report status to false
        comment.setCommentReport(false);
        // Update the comment in the database
        commentService.updateComment(comment);
    }

    
    
}
