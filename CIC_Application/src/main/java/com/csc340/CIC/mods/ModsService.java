package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.comment.CommentService;
import java.util.List;

@Service
public class ModsService {
    
    private final ModsRepository modsRepository;

    @Autowired
    public ModsService(ModsRepository modsRepository) {
        this.modsRepository = modsRepository;
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
    
}
