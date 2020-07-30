package edu.jhu.cloudsec.ctf.controllers;

import edu.jhu.cloudsec.ctf.VoteOption;
import edu.jhu.cloudsec.ctf.repositories.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private final VoteRepository voteRepository;

    @RequestMapping("/user")
    public String user(Model model) {
        return routeToPageWithUsername(model, "user");
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        long numRepublicanVotes = voteRepository.countVoteByVoteOptionEquals(VoteOption.REPUBLICAN);
        long numDemocratVotes = voteRepository.countVoteByVoteOptionEquals(VoteOption.DEMOCRAT);

        model.addAttribute("numRepublicanVotes", numRepublicanVotes);
        model.addAttribute("numDemocratVotes", numDemocratVotes);
        return routeToPageWithUsername(model, "admin");
    }

    @RequestMapping("/dev")
    public String dev(Model model) {
        return routeToPageWithUsername(model, "dev");
    }

    private String routeToPageWithUsername(Model model, String pageName) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "Unknown";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        model.addAttribute("name", username);
        return pageName;
    }

}