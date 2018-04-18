package com.paddysAssignment.four.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paddysAssignment.four.dto.UserRegistrationDto;
import com.paddysAssignment.four.model.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}