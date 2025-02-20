package com.Flatmate.FightResolver.service;

import com.Flatmate.FightResolver.DTO.VoteDTO;
import com.Flatmate.FightResolver.entities.Complaintentities;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.entities.Voteentities;
import com.Flatmate.FightResolver.repository.ComplainRepository;
import com.Flatmate.FightResolver.repository.UserRepository;
import com.Flatmate.FightResolver.repository.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComplainRepository complaintRepository;

    // Vote (Upvote or Downvote)
    public VoteDTO castVote(Long userId, Long complaintId, boolean isUpvote) {
        Userentities user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Complaintentities complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new EntityNotFoundException("Complaint not found"));

        Optional<Voteentities> existingVote = voteRepository.findByUserAndComplaint(user, complaint);

        if (existingVote.isPresent()) {
            Voteentities vote = existingVote.get();
            if (vote.isUpvote() == isUpvote) {
                voteRepository.delete(vote); // Remove vote if clicked again
                return null;
            } else {
                vote.setUpvote(isUpvote); // Switch upvote/downvote
                Voteentities updatedVote = voteRepository.save(vote);
                return mapToDTO(updatedVote);
            }
        }

        // Create new vote
        Voteentities newVote = new Voteentities(null, user, complaint, isUpvote);
        Voteentities savedVote = voteRepository.save(newVote);

        return mapToDTO(savedVote);
    }

    // Convert Entity to DTO
    private VoteDTO mapToDTO(Voteentities vote) {
        return new VoteDTO(
                vote.getId(),
                vote.getUser().getId(),
                vote.getComplaint().getId(),
                vote.isUpvote()
        );
    }
}
