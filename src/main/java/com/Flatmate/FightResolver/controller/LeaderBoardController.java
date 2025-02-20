package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.LeaderBoardDTO;
import com.Flatmate.FightResolver.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LeaderBoard")
public class LeaderBoardController {
    @Autowired
    private LeaderBoardService leaderboardService;

    @Autowired
    private LeaderBoardService leaderBoardService;

    @GetMapping("/{flatId}")
    public List<LeaderBoardDTO> getLeaderboard(@PathVariable Long flatId) {
        return leaderBoardService.getLeaderboardByFlat(flatId);
    }

    @PostMapping("/add")
    public LeaderBoardDTO addLeaderboardEntry(@RequestBody LeaderBoardDTO dto) {
        return leaderBoardService.addLeaderboardEntry(dto);
    }
}
