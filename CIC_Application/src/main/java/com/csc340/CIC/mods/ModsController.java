package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.csc340.CIC.comment.Comment;

import java.util.List;

@Controller
public class ModsController {

    private final ModsService modsService;

    @Autowired
    public ModsController(ModsService modsService){
        this.modsService = modsService;
    }

    @GetMapping("/mod/all")
    public String getReportedComments(Model model) {
        List<Comment> reportedComments = modsService.getReportedComments();
        model.addAttribute("reportedComments", reportedComments);
        return "mods/mods";
    }

    @GetMapping("/mod/delete")
    public String deleteReportedComment(@RequestParam("commentId") Long commentId) {
        modsService.deleteByID(commentId);
        return "redirect:/mod/all";
    }

    @GetMapping("/mod/ignore")
    public String ignoreReportedComment(@RequestParam("commentId") Long commentId) {
        modsService.ignoreReportedComment(commentId);
        return "redirect:/mod/all";
    }

    @PostMapping("/mod/report/user")
    public String reportUser(@RequestParam("userId") Long userId) {
        modsService.reportUser(userId);
        return "redirect:/mod/all";
    }
}
