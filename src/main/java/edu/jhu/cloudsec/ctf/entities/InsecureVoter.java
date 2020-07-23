package edu.jhu.cloudsec.ctf.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InsecureVoter {
    Long id;
    String username;
    String firstName;
    String lastName;
}
