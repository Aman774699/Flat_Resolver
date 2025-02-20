package com.Flatmate.FightResolver.repository;

import com.Flatmate.FightResolver.entities.Resolutionentities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolutionentities, Long> {
    Optional<Resolutionentities> findByComplaintId(Long complaintId);

}
