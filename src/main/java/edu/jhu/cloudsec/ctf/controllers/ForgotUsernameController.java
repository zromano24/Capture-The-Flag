package edu.jhu.cloudsec.ctf.controllers;

import edu.jhu.cloudsec.ctf.entities.InsecureVoter;
import edu.jhu.cloudsec.ctf.services.H2InjectionChecker;
import edu.jhu.cloudsec.ctf.services.UnsafeH2DatabaseConnection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ForgotUsernameController {
    private final UnsafeH2DatabaseConnection unsafeH2DatabaseConnection;

    @RequestMapping("/forgot")
    public String forgot() {
        return "forgot";
    }

    @GetMapping("/search")
    public String searchPage(String userSearch, Model model) {
        model.addAttribute("queryString", userSearch);

        if (H2InjectionChecker.inputCouldModifyTable(userSearch)) {
            model.addAttribute("error", "Looks like you might be trying to modify the database! You could be arrested for hacking!");
        } else {
            List<InsecureVoter> insecureVoterList = unsafeH2DatabaseConnection.findAllUsersByFirstName(userSearch);
            model.addAttribute("results", insecureVoterList);
        }
        return "search";
    }
}
