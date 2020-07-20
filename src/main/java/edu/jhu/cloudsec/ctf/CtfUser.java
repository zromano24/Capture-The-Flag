package edu.jhu.cloudsec.ctf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CtfUser {
    Long id;
    String username;
    String firstName;
    String lastName;
}
