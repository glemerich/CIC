package com.csc340.CIC.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    public void deleteComment(String commentid){

    }

    public String addComment(String username, String billId, String commentText) {

        return "";
    }

    // Add failure option to lead to post-failure in Controller
    public String addComment(Comment comment) {
        commentRepository.save(comment);
        return "Success";
    }

    public String editComment(String commentid, String content) {

        return "";
    }

    public List<Comment> getComments(String billId) {

        return commentRepository.findByBillId(billId);
    }
}
