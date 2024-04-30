package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.comment.CommentRepository;

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

    
    
}
