package com.csc340.CIC.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private final PollService pollService;

   
    public PollController(PollService pollService){
        this.pollService = pollService;
    }

    // Endpoint to display all polls for a representative
    @GetMapping("/all/{representativeId}")
    public String getAllPollsForRepresentative(@PathVariable String representativeId, Model model) {
        List<Poll> polls = pollService.getAllPollsForRepresentative(representativeId);
        model.addAttribute("polls", polls);
        return "poll/polls"; 
    }

    @PostMapping("/vote/{pollId}/{optionIndex}")
        public ResponseEntity<?> vote(@PathVariable int pollId, @PathVariable int optionIndex) {
        pollService.voteForOption(pollId, optionIndex);
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
