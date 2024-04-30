package com.csc340.CIC.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
                       
    public void deleteComment(long commentId){
        commentRepository.deleteById(commentId);                               
    }

    public String addComment(Comment comment) {
        commentRepository.save(comment);
        return "Success";
    }

    public String editComment(String commentId, String content) {
        // Implement edit comment functionality
        return "";
    }

    public List<Comment> getComments(String billId) {
        return commentRepository.findByBillId(billId);
    }

    public void reportComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
        comment.setReportedStatus(true);
        commentRepository.save(comment);
    }
}