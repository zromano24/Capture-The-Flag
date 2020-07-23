package edu.jhu.cloudsec.ctf.controllers;

import edu.jhu.cloudsec.ctf.VoteOption;
import edu.jhu.cloudsec.ctf.dtos.VoteDto;
import edu.jhu.cloudsec.ctf.entities.Vote;
import edu.jhu.cloudsec.ctf.repositories.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class VoteController {
    private final VoteRepository voteRepository;

    @PostMapping("/user/vote")
    public String vote(Model model, VoteDto userVote) {
        // check if the user has voted already
        // if they have 400
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "Unknown";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        Vote newVote = Vote.builder().voteOption(VoteOption.DEMOCRAT).voterUsername(username).build();

        voteRepository.save(newVote);


        return "user";
    }


    @PostMapping("/dev/mailInBallot")
    public String mailInVote(Model model, VoteDto userVote) {
        // must be dev
        // must be registered voter

        return "dev";
    }



}
