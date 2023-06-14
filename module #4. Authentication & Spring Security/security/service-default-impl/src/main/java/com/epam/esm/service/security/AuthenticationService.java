package com.epam.esm.service.security;

import com.epam.esm.core.exception.UserAlreadyExistsException;
import com.epam.esm.core.exception.UserNotFoundException;
import com.epam.esm.core.model.entity.User;
import com.epam.esm.core.model.enums.UserRole;
import com.epam.esm.core.payload.request.AuthRequest;
import com.epam.esm.core.payload.request.SignUpRequest;
import com.epam.esm.core.payload.response.AuthenticationResponse;
import com.epam.esm.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class AuthenticationService implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .userRole(UserRole.CUSTOMER)
                .build();
        log.debug("[AuthenticationService.signUp()] User to be saved: [{}}", user);

        if (userRepository.isExistsByEmail(user)) {
            log.error("[AuthenticationService.signUp()] User with given email:[{}] already exists.", user.getEmail());
            throw new UserAlreadyExistsException(String
                    .format("User with given email:[%s] already exists.", user.getEmail()));
        }

        userRepository.save(user);

        final String jwtToken = jwtService.generateToken(user);
        log.debug("[AuthenticationService.signUp()] Token: [{}] for user: [{}]", jwtToken, user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse signIn(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        String userEmail = authRequest.getEmail();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> {
                    log.error("[AuthenticationService.signIn()] User for given email:[{}] not found", userEmail);
                    throw new UserNotFoundException(String.format("User not found (email:[%s])", userEmail));
                });

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
