package com.epam.esm.api.controllers;

import com.epam.esm.api.AuthService;
import com.epam.esm.core.payload.request.AuthRequest;
import com.epam.esm.core.payload.request.SignUpRequest;
import com.epam.esm.core.payload.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
        // Create new user's account
        log.debug("[AuthController.registerUser()] Sign-Up request: [{}}", signUpRequest);

        AuthenticationResponse authenticationResponse = authService.signUp(signUpRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthRequest authRequest) {
        log.debug("[AuthController.registerUser()] Sign-In request: [{}}", authRequest);

        AuthenticationResponse authenticationResponse = authService.signIn(authRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) throws IOException {

        AuthenticationResponse authenticationResponse = authService.refreshToken(request);

        return ResponseEntity.ok(authenticationResponse);
    }
}