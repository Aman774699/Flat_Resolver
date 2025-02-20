package com.Flatmate.FightResolver.repository;

import com.Flatmate.FightResolver.DTO.LeaderBoardDTO;
import com.Flatmate.FightResolver.entities.Leaderboardentities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderBoardRepository extends JpaRepository<Leaderboardentities, Long> {
    List<Leaderboardentities> findByFlatId(Long flat_id);
}
