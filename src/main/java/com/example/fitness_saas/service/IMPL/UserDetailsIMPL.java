package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.entity.User;
import com.example.fitness_saas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsIMPL implements UserDetailsService {
@Autowired
UserRepository userRepository;
@Autowired
UserDetailsIMPL userDetailsService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username).get();
        return userDetailsService.build(user);
    }
}
