package com.Flatmate.FightResolver.service;

import com.Flatmate.FightResolver.DTO.LeaderBoardDTO;
import com.Flatmate.FightResolver.entities.Flatentities;
import com.Flatmate.FightResolver.entities.Leaderboardentities;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.repository.FlatRepository;
import com.Flatmate.FightResolver.repository.LeaderBoardRepository;
import com.Flatmate.FightResolver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaderBoardService {
    @Autowired
    private LeaderBoardRepository leaderboardRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private UserRepository userRepository;

    //  Method to update leaderboard for a user
    public LeaderBoardDTO updateLeaderboard(Long userId, Long flatId, int complaintsFiled, int complaintsResolved, int karmaChange) {
        Optional<Userentities> userOpt = userRepository.findById(userId);
        Optional<Flatentities> flatOpt = flatRepository.findById(flatId);

        if (userOpt.isEmpty() || flatOpt.isEmpty()) {
            throw new RuntimeException("User or Flat not found");
        }

        Userentities user = userOpt.get();
        Flatentities flat = flatOpt.get();

        // Check if leaderboard entry already exists
        Leaderboardentities leaderboard = leaderboardRepository.findByFlatId(flatId)
                .stream()
                .filter(lb -> lb.getUser().getId().equals(userId))
                .findFirst()
                .orElse(new Leaderboardentities(null, flat, user, 0, 0, 0));

        // Update values
        leaderboard.setComplaintsFiled(leaderboard.getComplaintsFiled() + complaintsFiled);
        leaderboard.setComplaintsResolved(leaderboard.getComplaintsResolved() + complaintsResolved);
        leaderboard.setTotalKarma(leaderboard.getTotalKarma() + karmaChange);

        Leaderboardentities savedLeaderboard = leaderboardRepository.save(leaderboard);

        return new LeaderBoardDTO(
                savedLeaderboard.getId(),
                flat.getFlat_id(),
                flat.getFlatCode(),
                user.getId(),
                user.getUsername(),
                savedLeaderboard.getComplaintsFiled(),
                savedLeaderboard.getComplaintsResolved(),
                savedLeaderboard.getTotalKarma()
        );
    }

    // Get leaderboard rankings by flat
    public List<LeaderBoardDTO> getLeaderboardByFlat(Long flatId) {
        return leaderboardRepository.findByFlatId(flatId).stream()
                .map(lb -> new LeaderBoardDTO(
                        lb.getId(),
                        lb.getFlat().getFlat_id(),
                        lb.getFlat().getFlatCode(),
                        lb.getUser().getId(),
                        lb.getUser().getUsername(),
                        lb.getComplaintsFiled(),
                        lb.getComplaintsResolved(),
                        lb.getTotalKarma()
                ))
                .sorted((a, b) -> Integer.compare(b.getTotalKarma(), a.getTotalKarma())) // Sort by highest karma
                .collect(Collectors.toList());
    }
}
