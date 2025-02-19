package com.Flatmate.FightResolver.service;

import com.Flatmate.FightResolver.entities.Userentities;
import com.Flatmate.FightResolver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailserviceimpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Userentities> user=userRepository.findByUsername(username);
        if(user.isPresent())
        {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getUsername())
                    .password(user.get().getPassword())
                    .roles(user.get().getRole().toString()).build();//changes of this because of only get Role authorities
        }
        throw new UsernameNotFoundException("User name does not exist"+username);
    }
}
