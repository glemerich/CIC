package com.csc340.CIC.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private final PollRepository pollRepository;

    
    public PollService(PollRepository pollRepository){
        this.pollRepository = pollRepository;
    }

    public List<Poll> getAllPollsForRepresentative(String representativeId) {
        List<Poll> polls = pollRepository.findAllByRepresentativeId(representativeId);
        return polls;
    }

    public void voteForOption(int pollId, int optionIndex) {
        // Retrieve the Poll entity
        Poll poll = pollRepository.findById(pollId).orElse(null);
        
        if (poll != null) {
            // Update the vote count based on the option index
            switch (optionIndex) {
                case 1:
                    poll.setOptionVoteCount1(poll.getOptionVoteCount1() + 1);
                    break;
                case 2:
                    poll.setOptionVoteCount2(poll.getOptionVoteCount2() + 1);
                    break;
                case 3:
                    poll.setOptionVoteCount3(poll.getOptionVoteCount3() + 1);
                    break;
                case 4:
                    poll.setOptionVoteCount4(poll.getOptionVoteCount4() + 1);
                    break;
                default:
                    // Handle invalid option index
                    break;
            }
            
            // Save the updated entity
            pollRepository.save(poll);
        }
    }

    // Method to retrieve a specific poll by ID
    public Poll getPollById(int pollId) {
        return pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found with ID: " + pollId));
    }
}
