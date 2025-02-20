package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.VoteDTO;
import com.Flatmate.FightResolver.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    // Vote (Upvote or Downvote)
    @PostMapping("/upvoteOrdownvote")
    public ResponseEntity<?> castVote(@RequestParam Long userId,
                                      @RequestParam Long complaintId,
                                      @RequestParam boolean isUpvote) {
        VoteDTO vote = voteService.castVote(userId, complaintId, isUpvote);
        if (vote == null) {
            return ResponseEntity.ok("Vote removed.");
        }
        return ResponseEntity.ok(vote);
    }
}
