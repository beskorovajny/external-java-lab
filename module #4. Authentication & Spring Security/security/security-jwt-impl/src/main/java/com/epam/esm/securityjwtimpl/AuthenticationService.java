package com.epam.esm.securityjwtimpl;

import com.epam.esm.api.AuthService;
import com.epam.esm.core.exception.InvalidTokenException;
import com.epam.esm.core.exception.TokenAlreadyExistsException;
import com.epam.esm.core.exception.UserAlreadyExistsException;
import com.epam.esm.core.exception.UserNotFoundException;
import com.epam.esm.core.jwt.Token;
import com.epam.esm.core.model.entity.User;
import com.epam.esm.core.model.enums.UserRole;
import com.epam.esm.core.payload.request.AuthRequest;
import com.epam.esm.core.payload.request.SignUpRequest;
import com.epam.esm.core.payload.response.AuthenticationResponse;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Slf4j
@Service
public class AuthenticationService implements AuthService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

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

        User savedUser = userRepository.save(user);
        log.debug("[AuthenticationService.signUp()] User has been saved, [{}]", savedUser);

        final String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Token token = Token.builder()
                .jwt(jwtToken)
                .user(savedUser)
                .revoked(false)
                .expired(false)
                .build();

        Token savedToken = tokenRepository.save(token);

        log.debug("[AuthenticationService.signUp()] Token: [{}] for user: [{}] has been saved", token, savedUser);

        return AuthenticationResponse
                .builder()
                .userEmail(user.getEmail())
                .userRole(user.getUserRole().getRoleName())
                .accessToken(savedToken.getJwt())
                .refreshToken(refreshToken)
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
                    return new UserNotFoundException(String.format("User not found (email:[%s])", userEmail));
                });

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Token token = saveTokenForUser(user, jwtToken);
        log.debug("[AuthenticationService.signIn()] Token: [{}] for user: [{}] has been saved", token, user);

        return AuthenticationResponse
                .builder()
                .userEmail(user.getEmail())
                .userRole(user.getUserRole().getRoleName())
                .accessToken(token.getJwt())
                .refreshToken(refreshToken)
                .build();
    }


    private Token saveTokenForUser(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .jwt(jwtToken)
                .expired(false)
                .revoked(false)
                .build();

        boolean isTokenExists = tokenRepository.isExists(token);

        if (isTokenExists) {
            log.error("[AuthenticationService.saveTokenForUser()] Token with given value:[{}] already exists.", jwtToken);
            throw new TokenAlreadyExistsException("Token already exists.");
        }

        return tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidByUser(user.getId());

        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        validUserTokens.forEach(tokenRepository::save);
    }

    public AuthenticationResponse refreshToken(String authHeader) {

        final String refreshToken;
        final String userEmail;
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.error("[AuthenticationService.refreshToken()] An exception occurs: headers is not valid");
            throw new IllegalArgumentException("An exception occurs: headers is not valid");
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> {
                        log.error("[AuthenticationService.refreshToken()] User for given email:[{}] not found", userEmail);
                        return new UserNotFoundException(String.format("User not found (email:[%s])", userEmail));
                    });

            if (!jwtService.isTokenValid(refreshToken, user)) {
                log.error("[AuthenticationService.refreshToken()] Token is not valid! [{}]", refreshToken);
                throw new InvalidTokenException("Token is not valid!");
            }

            String accessToken = jwtService.generateToken(user);

            revokeAllUserTokens(user);
            saveTokenForUser(user, accessToken);

            authenticationResponse = AuthenticationResponse.builder()
                    .userEmail(userEmail)
                    .userRole(user.getUserRole().getRoleName())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        return authenticationResponse;
    }
}
