package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.VoteDTO;
import com.Flatmate.FightResolver.entities.Voteentities;
import com.Flatmate.FightResolver.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    // Vote (Upvote or Downvote)
    @PostMapping("/upvoteOrdownvote")
    public ResponseEntity<?> castVote(@RequestBody VoteDTO voteDTO) {
        VoteDTO vote = voteService.castVote(voteDTO.getUserId(), voteDTO.getComplaintId(), voteDTO.isUpvote());
        if (vote == null) {
            return ResponseEntity.ok("Vote removed.");
        }
        return ResponseEntity.ok(vote);
    }
}
