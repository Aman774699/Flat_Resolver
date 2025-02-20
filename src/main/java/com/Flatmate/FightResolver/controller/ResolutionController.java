package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.ResolutionDTO;
import com.Flatmate.FightResolver.service.ResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Resolution")
public class ResolutionController {
    @Autowired
    private ResolutionService resolutionService;

    //  API to resolve a complaint
    @PostMapping("/resolve")
    public ResponseEntity<ResolutionDTO> resolveComplaint(
            @RequestParam Long complaintId,
            @RequestParam Long resolverId) {
        return ResponseEntity.ok(resolutionService.resolveComplaint(complaintId, resolverId));
    }

    //  API to get all resolutions
    @GetMapping("/getAllResolution")
    public ResponseEntity<List<ResolutionDTO>> getAllResolutions() {
        return ResponseEntity.ok(resolutionService.getAllResolutions());
    }

    //  API to get resolution by complaint ID
    @GetMapping("/complaint/{complaintId}")
    public ResponseEntity<ResolutionDTO> getResolutionByComplaint(@PathVariable Long complaintId) {
        return ResponseEntity.ok(resolutionService.getResolutionByComplaint(complaintId));
    }
}
