package com.csc340.CIC.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{pBillId}")
    public String getBillComments(@PathVariable String pBillId, Model pModel,
                                  @ModelAttribute("successMessage") String successMessage) {
        List<Comment> clist = commentService.getComments(pBillId); // Use the original bill ID from path variable
        pModel.addAttribute("clist", clist);
        if (successMessage != null && !successMessage.isEmpty()) {
            pModel.addAttribute("successMessage", successMessage);
        }
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
                                @RequestParam("billId") String billId,
                                RedirectAttributes redirectAttributes) {
        commentService.reportComment(commentId);
        redirectAttributes.addFlashAttribute("successMessage", "Comment reported successfully");
        String formattedBillId = BillIdFormatter.formatBillId(billId); // Format the billId only for redirection URL
        return "redirect:/bill/" + formattedBillId;
    }
}