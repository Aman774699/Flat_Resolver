package com.Flatmate.FightResolver.service;

import com.Flatmate.FightResolver.DTO.FlatDTO;
import com.Flatmate.FightResolver.entities.Flatentities;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.repository.FlatRepository;
import com.Flatmate.FightResolver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlatService {
    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private UserRepository userRepository;

    public FlatDTO createFlat(Flatentities flatentities) {
        if (flatRepository.findByFlatCode(flatentities.getFlatCode()).isPresent()) {
            throw new RuntimeException("Flat code already exists");
        }
        Flatentities savedFlat = flatRepository.save(flatentities);
        return new FlatDTO(savedFlat.getFlat_id(), savedFlat.getFlatCode(), new ArrayList<>());
    }

    public FlatDTO getFlatById(Long id) {
        Flatentities flat = flatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flat not found"));

        List<String> userNames = flat.getUsers().stream()
                .map(Userentities::getUsername)
                .collect(Collectors.toList());

        return new FlatDTO(flat.getFlat_id(), flat.getFlatCode(), userNames);
    }

    public FlatDTO getFlatByFlatCode(String flatCode) {
        Flatentities flat = flatRepository.findByFlatCode(flatCode)
                .orElseThrow(() -> new EntityNotFoundException("Flat not found"));

        List<String> userNames = flat.getUsers().stream()
                .map(Userentities::getUsername)
                .collect(Collectors.toList());

        return new FlatDTO(flat.getFlat_id(), flat.getFlatCode(), userNames);
    }

    public FlatDTO updateFlat(Long id, String newFlatCode) {
        Flatentities flat = flatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flat not found"));

        flat.setFlatCode(newFlatCode);
        Flatentities updatedFlat = flatRepository.save(flat);

        List<String> userNames = updatedFlat.getUsers().stream()
                .map(Userentities::getUsername)
                .collect(Collectors.toList());

        return new FlatDTO(updatedFlat.getFlat_id(), updatedFlat.getFlatCode(), userNames);
    }

    public void deleteFlat(Long id) {
        Flatentities flat = flatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flat not found"));
        flatRepository.delete(flat);
    }
}
