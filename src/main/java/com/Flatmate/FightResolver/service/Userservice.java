package com.Flatmate.FightResolver.service;

import com.Flatmate.FightResolver.DTO.UserDTO;
import com.Flatmate.FightResolver.entities.Flatentities;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class Userservice {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FlatService flatService;

    public UserDTO createUser(Userentities user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // Encrypt password
        user.setPassword(user.getPassword());
        Flatentities flat=user.getFlat();
        flatService.createFlat(flat);

        Userentities savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
    }

    public UserDTO getUserById(Long id) {
        Userentities user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return new UserDTO(user.getUsername(), user.getEmail(), user.getRole());
    }

    public UserDTO updateUser(Long id, Userentities updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setEmail(updatedUser.getEmail());
                    return userRepository.save(existingUser);
                })
                .map(user -> new UserDTO(user.getUsername(), user.getEmail(), user.getRole()))
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    public void deactivateUser(Long id) {
        Userentities user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        user.setIsactive(false);
        userRepository.save(user);
    }
}
