package com.csc340.CIC.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/bill/all";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "redirect:/user/login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "/user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        String registrationResult = userService.registerUser(user);
        if ("User registered successfully".equals(registrationResult)) {
            return "redirect:/user/login";
        } else {
            model.addAttribute("error", true);
            return "redirect:/user/register?error=true";
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "bill/all";
    }

    /* 
    @GetMapping("/{username}")
    @ResponseBody
    public User getUserByUsername(@PathVariable String username) {
        return userService.loadUserByUsername(username);
    }
    */

}
