package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.ComplainDTO;
import com.Flatmate.FightResolver.entities.Complaintentities;
import com.Flatmate.FightResolver.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Complaints")
public class ComplainController {
    @Autowired
    private ComplainService complaintService;

    // Create Complaint
    @PostMapping("/createcomplain/{userId}/{flatId}")
    public ResponseEntity<ComplainDTO> createComplaint(@RequestBody Complaintentities complaintentities, @PathVariable Long userId,@PathVariable Long flatId) {
        ComplainDTO complaint = complaintService.createComplaint(complaintentities,userId,flatId);
        return ResponseEntity.status(HttpStatus.CREATED).body(complaint);
    }

    // Get Complaint by ID
    @GetMapping("/getByID/{id}")
    public ResponseEntity<ComplainDTO> getComplaintById(@PathVariable Long id) {
        ComplainDTO complaint = complaintService.getComplaintById(id);
        return ResponseEntity.ok(complaint);
    }

    // Get Complaints by Flat
    @GetMapping("/flat/{flatCode}")
    public ResponseEntity<List<ComplainDTO>> getComplaintsByFlat(@PathVariable String flatCode) {
        List<ComplainDTO> complaints = complaintService.getComplaintsByFlat(flatCode);
        return ResponseEntity.ok(complaints);
    }

    // Upvote Complaint
    @PutMapping("/{id}/upvote")
    public ResponseEntity<ComplainDTO> upvoteComplaint(@PathVariable Long id) {
        ComplainDTO complaint = complaintService.upvoteComplaint(id);
        return ResponseEntity.ok(complaint);
    }

    // Downvote Complaint
    @PutMapping("/{id}/downvote")
    public ResponseEntity<ComplainDTO> downvoteComplaint(@PathVariable Long id) {
        ComplainDTO complaint = complaintService.downvoteComplaint(id);
        return ResponseEntity.ok(complaint);
    }

    // Resolve Complaint
    @PutMapping("/{id}/resolve")
    public ResponseEntity<ComplainDTO> resolveComplaint(@PathVariable Long id) {
        ComplainDTO complaint = complaintService.resolveComplaint(id);
        return ResponseEntity.ok(complaint);
    }

    // Delete Complaint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.noContent().build();
    }
}
