package edu.jhu.cloudsec.ctf.entities;

import edu.jhu.cloudsec.ctf.VoteOption;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor()
@Setter
@Getter
@Table(name = "VOTES")
public class Vote {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private VoteOption voteOption;

    @Column
    private String voterUsername;
}
