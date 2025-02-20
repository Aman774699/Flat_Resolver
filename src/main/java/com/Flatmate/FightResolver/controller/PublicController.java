package com.Flatmate.FightResolver.controller;

import com.Flatmate.FightResolver.DTO.UserDTO;
import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.service.UserDetailserviceimpl;
import com.Flatmate.FightResolver.service.Userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import  com.Flatmate.FightResolver.utility.jwtUtils;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    Userservice userService;
  @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailserviceimpl userDetailServiceimpl;
    @Autowired
    jwtUtils jwtUtil;
    // Create User
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody Userentities user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

//    Get authentication key
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Userentities userEntity) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
            UserDetails userDetail=userDetailServiceimpl.loadUserByUsername(userEntity.getUsername());
            String jwt=jwtUtil.generateToken(userDetail.getUsername());
            return new  ResponseEntity<>(jwt,HttpStatus.OK);
        } catch (Exception e) {
//            log.error("Exception is...", e);
            return new ResponseEntity<>("Error Generated",HttpStatus.BAD_GATEWAY);
        }
    }


}


