package com.csc340.CIC.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.csc340.CIC.representative.*;
import com.csc340.CIC.user.User;
import com.csc340.CIC.user.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private final PollService pollService;

    @Autowired
    private final UserService userService;

    @Autowired 
    private final RepresentativeService representativeService;
   
    public PollController(PollService pollService, UserService userService, RepresentativeService representativeService){
        this.pollService = pollService;
        this.userService = userService;
        this.representativeService = representativeService;
    }

    // Endpoint to display all polls for a representative
    @GetMapping("/all/{representativeId}")
    public String getAllPollsForRepresentative(@PathVariable String representativeId, Model model) {
        List<Poll> polls = pollService.getAllPollsForRepresentative(representativeId);
        model.addAttribute("polls", polls);
        model.addAttribute("poll", new Poll());
        model.addAttribute("representativeId", representativeId);
        return "poll/polls"; 
    }

    @PostMapping("/vote/{pollId}/{optionIndex}")
    public ResponseEntity<?> vote(@PathVariable int pollId, @PathVariable int optionIndex) {
    pollService.voteForOption(pollId, optionIndex);
    return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public String createPoll(@ModelAttribute("poll") Poll poll, @RequestParam("representativeId") String representativeId, Model model, @RequestParam("username") String username) {
        // Set the representative ID for the new poll
        poll.setRepresentativeId(representativeId); 
        
        Optional <User> user = userService.getUserByUsername(username);
        Long userId = user.isPresent() ? user.get().getUserId() : null;
        Representative representative = representativeService.getRepresentativeByUserId(userId);
        var repId = representative.getId();

        if (repId == representativeId){
            // Create the new poll
            pollService.createPoll(poll);
        }
        else{
            model.addAttribute("errorMessage", "You are not authorized to create a poll for this representative.");
        }

        // Redirect back to the page displaying all polls
        return "redirect:/representative/" + representativeId;
    }

    @PostMapping("/delete/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable int pollId) {
        pollService.deletePoll(pollId);
        return ResponseEntity.ok().build();
    }


    // Endpoint to display a specific poll
    @GetMapping("/{pollId}")
    public String getPollDetails(@PathVariable int pollId, Model model) {
        Poll poll = pollService.getPollById(pollId);
        model.addAttribute("poll", poll);
        return "poll/poll-details";
    }
}
