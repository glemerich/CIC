package com.csc340.CIC.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            // Spring Security will handle authentication automatically
            // Redirect to the home page after successful login
            return "redirect:/bill/all";
        } catch (Exception e) {
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

}
