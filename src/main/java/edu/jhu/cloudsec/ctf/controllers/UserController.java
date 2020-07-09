package edu.jhu.cloudsec.ctf.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/user")
    public String user(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "Unknown";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        model.addAttribute("name", username);
        return "user";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "Unknown";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        model.addAttribute("name", username);
        return "admin";
    }

}