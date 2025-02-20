package com.Flatmate.FightResolver.service;

import com.Flatmate.FightResolver.DTO.ResolutionDTO;
import com.Flatmate.FightResolver.entities.Complaintentities;
import com.Flatmate.FightResolver.entities.Resolutionentities;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.repository.ComplainRepository;
import com.Flatmate.FightResolver.repository.ResolutionRepository;
import com.Flatmate.FightResolver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResolutionService {
    @Autowired
    private ResolutionRepository resolutionRepository;

    @Autowired
    private ComplainRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    //  Method to resolve a complaint
    public ResolutionDTO resolveComplaint(Long complaintId, Long resolverId) {
        Optional<Complaintentities> complaintOpt = complaintRepository.findById(complaintId);
        Optional<Userentities> resolverOpt = userRepository.findById(resolverId);

        if (complaintOpt.isEmpty() || resolverOpt.isEmpty()) {
            throw new RuntimeException("Complaint or Resolver not found");
        }

        Complaintentities complaint = complaintOpt.get();
        Userentities resolver = resolverOpt.get();

        Resolutionentities resolution = new Resolutionentities();
        resolution.setComplaint(complaint);
        resolution.setResolver(resolver);


        Resolutionentities savedResolution = resolutionRepository.save(resolution);

        return new ResolutionDTO(
                savedResolution.getId(),
                complaint.getId(),
                complaint.getTitle(),
                resolver.getId(),
                resolver.getUsername(),
                savedResolution.getResolvedAt()
        );
    }

    // Get all resolutions
    public List<ResolutionDTO> getAllResolutions() {
        return resolutionRepository.findAll().stream().map(resolution -> new ResolutionDTO(
                resolution.getId(),
                resolution.getComplaint().getId(),
                resolution.getComplaint().getTitle(),
                resolution.getResolver().getId(),
                resolution.getResolver().getUsername(),
                resolution.getResolvedAt()
        )).collect(Collectors.toList());
    }

    //  Get resolution by complaint ID
    public ResolutionDTO getResolutionByComplaint(Long complaintId) {
        Resolutionentities resolution = resolutionRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Resolution not found for this complaint"));

        return new ResolutionDTO(
                resolution.getId(),
                resolution.getComplaint().getId(),
                resolution.getComplaint().getTitle(),
                resolution.getResolver().getId(),
                resolution.getResolver().getUsername(),
                resolution.getResolvedAt()
        );
    }
}
