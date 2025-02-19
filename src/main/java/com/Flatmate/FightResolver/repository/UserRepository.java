package com.Flatmate.FightResolver.repository;

import com.Flatmate.FightResolver.entities.Userentities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Userentities,Long> {
    Optional<Userentities> findByUsername(String username);
    Optional<Userentities> findByEmail(String email);
}
