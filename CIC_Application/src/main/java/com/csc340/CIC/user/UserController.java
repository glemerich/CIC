package com.csc340.CIC.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String showLoginPage() {
        return "/user/login";
    }

    @PostMapping("/login")
public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
    try {
        // Authenticate the user using Spring Security's authentication mechanisms
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        // If authentication succeeds, set the authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Redirect to the home page after successful login
        return "redirect:/bill/all";
    } catch (AuthenticationException e) {
        // Log the error message
        e.printStackTrace();
        return "redirect:/user/login?error=true";
    }
}



    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        // Add an empty User object to the model for registration form binding
        model.addAttribute("user", new User());
        return "/user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            String registrationResult = userService.registerUser(user);
            if (registrationResult.equals("User registered successfully")) {
                return "redirect:/user/login";
            } else {
                // Add error attribute to model
                model.addAttribute("error", true);
                return "redirect:/user/register?error=true";
            }
        } catch (Exception e) {
            // Log the error message
            e.printStackTrace();
            return "redirect:/user/register?error=true";
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "bill/all";
    }

    @GetMapping("/{username}")
    @ResponseBody
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


}
