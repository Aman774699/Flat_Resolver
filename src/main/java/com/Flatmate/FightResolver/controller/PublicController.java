package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.UserDTO;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    Userservice userService;

    // Create User
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody Userentities user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }


}
