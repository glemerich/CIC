package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.CIC.comment.Comment;
import com.csc340.CIC.user.UserRepository;
import com.csc340.CIC.comment.CommentService;
import java.util.Optional;

@Service
public class ModsService {
    
    private final ModsRepository modsRepository;
    private final CommentService commentService;
    private final UserRepository userRepository;

    @Autowired
    public ModsService(ModsRepository modsRepository, CommentService commentService, UserRepository userRepository) {
        this.modsRepository = modsRepository;
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    // Other methods...

    public void reportUser(Long userId) {
        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Set the user's reported flag to true
            user.setUserReported(true);
            
            // Save the updated user
            userRepository.save(user);
        } else {
            // Handle the case where the user does not exist
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }
}
