package com.Flatmate.FightResolver.service;

import com.Flatmate.FightResolver.DTO.ComplainDTO;
import com.Flatmate.FightResolver.entities.Complaintentities;
import com.Flatmate.FightResolver.entities.Flatentities;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.repository.ComplainRepository;
import com.Flatmate.FightResolver.repository.FlatRepository;
import com.Flatmate.FightResolver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplainService {
    @Autowired
    private ComplainRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlatRepository flatRepository;

//Create complain
    public ComplainDTO createComplaint(Complaintentities complaintentities, Long userId, Long flatId) {
        Userentities user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Flatentities flat = flatRepository.findById(flatId)
                .orElseThrow(() -> new EntityNotFoundException("Flat not found"));
        Complaintentities savedComplaint = complaintRepository.save(complaintentities);

        return mapToDTO(savedComplaint);
    }

//    getComplainById
    public ComplainDTO getComplaintById(Long id) {
        Complaintentities complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Complaint not found"));
        return mapToDTO(complaint);
    }

//    GetComplainbyflatcode
    public List<ComplainDTO> getComplaintsByFlat(String flatCode) {
        List<Complaintentities> complaints = complaintRepository.findByFlat_FlatCode(flatCode);
        return complaints.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    // Upvote Complaint
    public ComplainDTO upvoteComplaint(Long id) {
        Complaintentities complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Complaint not found"));

        complaint.setUpvotes(complaint.getUpvotes() + 1);
        Complaintentities updatedComplaint = complaintRepository.save(complaint);

        return mapToDTO(updatedComplaint);
    }

    // Downvote Complaint
    public ComplainDTO downvoteComplaint(Long id) {
        Complaintentities complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Complaint not found"));

        complaint.setDownvotes(complaint.getDownvotes() + 1);
        Complaintentities updatedComplaint = complaintRepository.save(complaint);

        return mapToDTO(updatedComplaint);
    }
    // Resolve Complaint
    public ComplainDTO resolveComplaint(Long id) {
        Complaintentities complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Complaint not found"));

        complaint.setResolved(true);
        Complaintentities updatedComplaint = complaintRepository.save(complaint);

        return mapToDTO(updatedComplaint);
    }

    // Delete Complaint
    public void deleteComplaint(Long id) {
        Complaintentities complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Complaint not found"));
        complaintRepository.delete(complaint);
    }
//    Mapping Method
    private ComplainDTO mapToDTO(Complaintentities complaint) {
        return new ComplainDTO(
                complaint.getId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getType(),
                complaint.getSeverity(),
                complaint.getFiledBy().getUsername(),
                complaint.getFlat().getFlatCode(),
                complaint.getUpvotes(),
                complaint.getDownvotes(),
                complaint.isResolved(),
                complaint.getTimestamp()
        );
    }
}
