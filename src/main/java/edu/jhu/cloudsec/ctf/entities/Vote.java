package edu.jhu.cloudsec.ctf.entities;

import edu.jhu.cloudsec.ctf.VoteOption;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor()
@Setter
@Getter
@Table(name = "VOTES")
public class Vote {

    @Id
    @Column
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private VoteOption voteOption;

    @Column
    private String voterUsername;
}
