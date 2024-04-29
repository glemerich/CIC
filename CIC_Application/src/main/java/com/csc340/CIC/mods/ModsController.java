package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ModsController {
  
  
    @Autowired

    private final ModsService modsService;
  

    
    
    public ModsController(ModsService modsService){
        this.modsService = modsService;
    }
    
    @GetMapping("/mod/all")
    public String getReportedComments(Model model) {
        // Assuming you have a method in ModsService to get reported comments
        List<Report> reportedComments = modsService.getReportedComments();
       //bill id and comment id together.
        // Add the fetched data to the model to be rendered in the view
        model.addAttribute("commentText", reportedComments);

        // Return the name of the Thymeleaf template to render the data
        return "mods/mods"; // Replace "reportedComments" with your actual template name
    }

    @GetMapping("/mod/delete")
    public String deleteReportedComment(@RequestParam("commentId") Long commentId) {
        modsService.deleteByID(commentId);
        return "redirect:/mod/all";
    }


}
