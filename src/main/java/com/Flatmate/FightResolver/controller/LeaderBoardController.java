package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.LeaderBoardDTO;
import com.Flatmate.FightResolver.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LeaderBoard")
public class LeaderBoardController {
    @Autowired
    private LeaderBoardService leaderboardService;

    //  API to update leaderboard
    @PostMapping("/update")
    public ResponseEntity<LeaderBoardDTO> updateLeaderboard(
            @RequestParam Long userId,
            @RequestParam Long flatId,
            @RequestParam int complaintsFiled,
            @RequestParam int complaintsResolved,
            @RequestParam int karmaChange) {
        return ResponseEntity.ok(leaderboardService.updateLeaderboard(userId, flatId, complaintsFiled, complaintsResolved, karmaChange));
    }

    // API to get leaderboard by flat
    @GetMapping("/flat/{flatId}")
    public ResponseEntity<List<LeaderBoardDTO>> getLeaderboardByFlat(@PathVariable Long flatId) {
        return ResponseEntity.ok(leaderboardService.getLeaderboardByFlat(flatId));
    }
}
