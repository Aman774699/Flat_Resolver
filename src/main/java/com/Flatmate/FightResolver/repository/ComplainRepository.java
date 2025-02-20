package com.Flatmate.FightResolver.repository;

import com.Flatmate.FightResolver.entities.Complaintentities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplainRepository extends JpaRepository<Complaintentities,Long> {
    List<Complaintentities> findByFlat_FlatCode(String flatCode);
}
