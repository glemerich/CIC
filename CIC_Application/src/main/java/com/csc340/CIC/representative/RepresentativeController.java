package com.csc340.CIC.representative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/representative")
@Controller
public class RepresentativeController {

    @Autowired
    private final RepresentativeService representativeService;

    public RepresentativeController(RepresentativeService representativeService) {
        this.representativeService = representativeService;
    }

    @GetMapping("/all")
    public String getAllRepresentatives(Model model) {
        model.addAttribute("representatives", representativeService.getAllRepresentatives());
        return "representative/list-representatives";
    }

    @GetMapping("/{representativeID}")
    public String getRepresentativeDetails(@PathVariable String representativeID, Model model) {
        try {
            RepresentativeDetails representativeDetails = representativeService.getRepresentativeDetails(representativeID);
            model.addAttribute("representative", representativeDetails);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error occurred while fetching representative details.");
        }
        return "representative/individual-representative";
    }

    @PostMapping("/{representativeID}/update-biography")
    public String updateBiography(@PathVariable String representativeID, @RequestParam String newBiography, Model model) {
        try {
            RepresentativeDetails updatedRepresentative = representativeService.updateBiography(representativeID, newBiography);
            model.addAttribute("representative", updatedRepresentative);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error occurred while updating biography.");
        }
        return "redirect:/representative/" + representativeID;
    }

    @GetMapping("/{representativeID}/edit-biography")
    public String showEditBiographyForm(@PathVariable String representativeID, Model model) {
    Representative representative = representativeService.getRepresentativeById(representativeID)
    .orElseThrow(() -> new IllegalArgumentException("Representative not found with ID: " + representativeID));
        if (representative != null) {
        model.addAttribute("representative", representative);
        return "representative/edit-biography"; 
    } else {
        model.addAttribute("errorMessage", "Representative not found with ID: " + representativeID);
        return "redirect:/representative/" + representativeID; // Redirect to representative page
    }
}

}
