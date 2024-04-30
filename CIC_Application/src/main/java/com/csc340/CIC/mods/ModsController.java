package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/mod")
public class ModsController {
    
    @Autowired
    private final ModsService modsService;

    @Autowired UserService userService;

    
    public ModsController(ModsService modsService, UserService userService){
        this.modsService = modsService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getReportedComments(Model model) {
        List<Comment> reportedComments = modsService.getReportedComments();
        model.addAttribute("reportedComments", reportedComments);
        return "mods/mods";
    }

    @GetMapping("/delete")
    public String deleteReportedComment(@RequestParam("commentId") Long commentId) {
        modsService.deleteByID(commentId);
        return "redirect:/mod/all";
    }

    @GetMapping("/ignore")
    public String ignoreReportedComment(@RequestParam("commentId") Long commentId) {
    modsService.ignoreReportedComment(commentId);
    return "redirect:/mod/all";
    }

    @GetMapping("/report")
    public String reportUser(@RequestParam("username") String username, @RequestParam("commentId") Long commentId, Model model) {
    try {
        // Call the UserService method to report the user
        modsService.reportUser(username);
        modsService.ignoreReportedComment(commentId);
        // Redirect to a success page or any other page as needed
        return "redirect:/mod/all";
    } catch (Exception e) {
        // Log the error message
        e.printStackTrace();
        // Redirect to an error page or any other page as needed
        return "redirect:/error";
    }
}
}
