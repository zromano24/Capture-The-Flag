package edu.jhu.cloudsec.ctf.dtos;

import edu.jhu.cloudsec.ctf.VoteOption;
import lombok.Value;

@Value
public class VoteDto {
    VoteOption voteValue;
}
