package edu.jhu.cloudsec.ctf.controllers;

import edu.jhu.cloudsec.ctf.dtos.MailInVoteDto;
import edu.jhu.cloudsec.ctf.dtos.VoteDto;
import edu.jhu.cloudsec.ctf.entities.Vote;
import edu.jhu.cloudsec.ctf.repositories.VoteRepository;
import edu.jhu.cloudsec.ctf.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@Log4j2
public class VoteController {
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;

    @PostMapping("/user/vote")
    public String vote(Model model, VoteDto userVote) {
        // check if the user has voted already
        // if they have 400
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "Unknown";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        if (voteRepository.existsByVoterUsernameEquals(username)) {
            return "error/500";
        }

        Vote newVote = Vote.builder().voteOption(userVote.getVoteValue()).voterUsername(username).build();
        voteRepository.save(newVote);
        log.info("User: {} has voted!", username);

        return "redirect:/user";
    }


    @PostMapping("/dev/mailInBallot")
    public String mailInVote(MailInVoteDto mailInVoteDto, RedirectAttributes redirectAttributes) {
        // must be dev
        // must be registered voter

        if (!voterRepository.existsByUsernameEquals(mailInVoteDto.getUsername())) {
            return "error/userNotFound";
        }

        if (voteRepository.existsByVoterUsernameEquals(mailInVoteDto.getUsername())) {
            return "error/500";
        }

        redirectAttributes.addFlashAttribute("finalFlag", "FLAG 4 PASSPHRASE: \"THIS_IS_THE_FINAL_FLAG__HOPE_YOU_HAD_FUN\"");
        Vote newVote = Vote.builder().voteOption(mailInVoteDto.getVoteValue()).voterUsername(mailInVoteDto.getUsername()).build();
        voteRepository.save(newVote);

        return "redirect:/dev";
    }



}
