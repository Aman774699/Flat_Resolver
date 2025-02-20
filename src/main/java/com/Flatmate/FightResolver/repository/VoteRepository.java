package com.Flatmate.FightResolver.repository;

import com.Flatmate.FightResolver.entities.Complaintentities;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.entities.Voteentities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Voteentities,Long> {
    Optional<Voteentities> findByUserAndComplaint(Userentities user, Complaintentities complaint);
}
