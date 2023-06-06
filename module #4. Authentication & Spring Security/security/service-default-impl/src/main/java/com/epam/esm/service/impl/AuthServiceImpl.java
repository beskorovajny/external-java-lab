package com.epam.esm.service.impl;

import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.exception.UserNotFoundException;
import com.epam.esm.core.model.entity.User;
import com.epam.esm.core.model.enums.UserRole;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.AuthService;
import com.epam.esm.service.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MappingService<User, UserDTO> mappingService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param eMail the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.findByEmail(eMail)
                .map(mappingService::mapToDto)
                .orElseThrow(() -> {
                    log.error("[AuthService.loadUserByUsername()] User for given eMail:[{}] not found", eMail);
                    throw new UserNotFoundException(String.format("User not found (eMail:[%s])", eMail));
                });
        return mappingService.mapFromDto(userDTO);
    }

    @Override
    public UserDTO signIn(UserDTO userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        return mappingService.mapToDto(user);
    }

    @Override
    public UserDTO signUp(UserDTO userDto, UserRole role) {
        User user = mappingService.mapFromDto(userDto);
        log.debug("[AuthService.signUp()] Registering a new user with email {}", user.getEmail());

        user.setUserRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);
        log.debug("[AuthService.signUp()] User with id {} successfully registered", user.getId());

        return signIn(userDto);
    }

    @Override
    public void signOut() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("[AuthService.signOut()] Singing out user with email {}", user.getEmail());
        SecurityContextHolder.clearContext();
    }
}
