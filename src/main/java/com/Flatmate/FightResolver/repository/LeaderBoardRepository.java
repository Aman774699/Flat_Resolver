package com.Flatmate.FightResolver.repository;

import com.Flatmate.FightResolver.entities.Leaderboardentities;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderBoardRepository extends JpaRepository<Leaderboardentities, Long> {
    @Query("SELECT l FROM Leaderboardentities l WHERE l.flat.flat_id = :flatId")
    List<Leaderboardentities> findByFlatId(@Param("flatId") Long flatId);
}
