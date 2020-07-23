package edu.jhu.cloudsec.ctf.repositories;

import edu.jhu.cloudsec.ctf.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    boolean existsByUsernameEquals(String username);
}
