package com.Flatmate.FightResolver.repository;

import com.Flatmate.FightResolver.entities.Flatentities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlatRepository extends JpaRepository<Flatentities,Long> {
    Optional<Flatentities> findByFlatCode(String flatCode);
}
