package com.JOWHAN.damn;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    public AuthController() {
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String message, Model model) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "login"; // returns login.html
    }

    // LOGIN PROCESS
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {

        if ("Joehanne".equals(username) && "1234".equals(password)) {
            session.setAttribute("username", username);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute AppUser user, Model model) {
        if (user.getUsername() == null || user.getUsername().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()) {
            model.addAttribute("error", "Username and password are required.");
            return "register";
        }

        // You can add the user to a list or database here if needed
        return "redirect:/login?message=Registration successful";
    }



    // LOGOUT HANDLING
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?message=You have been logged out";
    }
}
