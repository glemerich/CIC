package com.csc340.CIC.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{pBillId}")
    public String getBillComments(@PathVariable String pBillId, Model pModel) {
        List<Comment> clist = commentService.getComments(pBillId);
        pModel.addAttribute("clist", clist);
        return "comment/list-comments";
    }

    @PostMapping("/post")
    public String postComment(@RequestBody Comment comment) {
        comment.setCommentDate(new Date());
        String response = commentService.addComment(comment);
        return response.equals("failure") ? "comment/post-failure" : "comment/post-success";
    }

    @PostMapping("/report")
    public String reportComment(@RequestParam("commentId") Long commentId,
                                @RequestParam("billId") String billId) {
        commentService.reportComment(commentId);
        return "comment/list-comments";
    }


    /*  
    @DeleteMapping("/delete")
    public String deleteComment(@RequestBody Comment comment){
        commentService.deleteComment(comment.getCommentId());
        return "comment/post-success";
    }
    */
}

