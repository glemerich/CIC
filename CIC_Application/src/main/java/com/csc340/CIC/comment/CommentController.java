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
        System.out.println("get comments..."+ pBillId);
        System.out.println("model: "+ pBillId);

        List<Comment> clist = commentService.getComments(pBillId);
        pModel.addAttribute("clist", clist);

        System.out.println(clist);

        return "comment/list-comments";
    }

    @PostMapping("/post")
    public String postComment(@RequestBody Comment comment) {
        System.out.println(comment);

        // Set comment date
        comment.setCommentDate(new Date());

        System.out.println(comment);
        // Save the comment to the database
        String response = commentService.addComment(comment);
        if (response == "failure") {
            return "comment/post-failure";
        }
        else {
            return "comment/post-success";
        }
    }
}    
