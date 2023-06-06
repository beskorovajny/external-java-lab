package com.epam.esm.service;

import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    UserDTO signIn(UserDTO userDto);

    UserDTO signUp(UserDTO userDto, UserRole role);

    void signOut();
}
