package edu.jhu.cloudsec.ctf.controllers;

import edu.jhu.cloudsec.ctf.services.H2InjectionChecker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForgotUsernameController {

    @RequestMapping("/forgot")
    public String forgot() {
        return "forgot";
    }

    @GetMapping("/search")
    public String searchPage(String userSearch, Model model) {
        model.addAttribute("queryString", userSearch);

        if (H2InjectionChecker.inputCouldModifyTable(userSearch)) {
            model.addAttribute("results", "Looks like you might be trying to modify the database! You could be arrested for hacking!");
        } else {
            model.addAttribute("results", "No results!");
        }
        return "search";
    }
}
