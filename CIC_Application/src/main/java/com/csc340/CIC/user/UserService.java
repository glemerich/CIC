package com.csc340.CIC.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {

        // Encode the password and set the user's status based on the role
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRequestedRole(user.getRole()); // Save the requested role
        user.setApprovalStatus("user".equals(user.getRole()) ? "approved" : "pending");
        user.setStatus(true);

        // Save the user in the database
        userRepository.save(user);
        return "User registered successfully";
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public List<User> findPendingUsers() {
        // Retrieve users with "pending" approval status
        return userRepository.findByApprovalStatus("pending");
    }

    public Optional <User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
