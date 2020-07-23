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
@Table(name = "VOTERS")
public class Voter {

    @Id
    @Column
    private long id;

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;
}
