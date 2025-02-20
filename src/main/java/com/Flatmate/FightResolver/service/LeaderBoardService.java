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
    private LeaderBoardRepository leaderBoardRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LeaderBoardDTO> getLeaderboardByFlat(Long flatId) {
        Optional<Flatentities> flatentities=flatRepository.findById(flatId);
        if(flatentities.isPresent()) {
            List<Leaderboardentities> leaderboard = leaderBoardRepository.findByFlatId(flatentities.get().getFlat_id());
            return leaderboard.stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        return null;
        }

    public LeaderBoardDTO addLeaderboardEntry(LeaderBoardDTO dto) {
        Flatentities flat = flatRepository.findById(dto.getFlatId()).orElseThrow(() -> new RuntimeException("Flat not found"));
        Userentities user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Leaderboardentities entity = new Leaderboardentities();
        entity.setFlat(flat);
        entity.setUser(user);
        entity.setComplaintsFiled(dto.getComplaintsFiled());
        entity.setComplaintsResolved(dto.getComplaintsResolved());
        entity.setTotalKarma(dto.getTotalKarma());

        Leaderboardentities savedEntity = leaderBoardRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    private LeaderBoardDTO convertToDTO(Leaderboardentities entity) {
        LeaderBoardDTO dto = new LeaderBoardDTO();
        dto.setId(entity.getId());
        dto.setFlatId(entity.getFlat().getFlat_id());
        dto.setUserId(entity.getUser().getId());
        dto.setComplaintsFiled(entity.getComplaintsFiled());
        dto.setComplaintsResolved(entity.getComplaintsResolved());
        dto.setTotalKarma(entity.getTotalKarma());
        return dto;
    }
}
