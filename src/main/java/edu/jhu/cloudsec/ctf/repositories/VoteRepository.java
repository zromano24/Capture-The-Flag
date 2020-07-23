package edu.jhu.cloudsec.ctf.repositories;

import edu.jhu.cloudsec.ctf.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
