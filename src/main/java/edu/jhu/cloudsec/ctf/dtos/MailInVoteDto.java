package edu.jhu.cloudsec.ctf.dtos;

import edu.jhu.cloudsec.ctf.VoteOption;
import lombok.NonNull;
import lombok.Value;

@Value
public class MailInVoteDto {
    VoteOption voteValue;
    @NonNull String username;
}
