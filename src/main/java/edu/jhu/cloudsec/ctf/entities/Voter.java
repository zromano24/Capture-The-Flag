package edu.jhu.cloudsec.ctf.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
