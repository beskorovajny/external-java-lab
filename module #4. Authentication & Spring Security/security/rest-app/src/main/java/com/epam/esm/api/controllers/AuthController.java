package com.epam.esm.api.controllers;

import com.epam.esm.api.AuthService;
import com.epam.esm.core.payload.request.AuthRequest;
import com.epam.esm.core.payload.request.SignUpRequest;
import com.epam.esm.core.payload.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.debug("[AuthController.registerUser()] Sign-Up request: [{}}", signUpRequest);

        AuthenticationResponse authenticationResponse = authService.signUp(signUpRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        log.debug("[AuthController.registerUser()] Sign-In request: [{}}", authRequest);

        AuthenticationResponse authenticationResponse = authService.signIn(authRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {

        AuthenticationResponse authenticationResponse = authService.refreshToken(auth);

        return ResponseEntity.ok(authenticationResponse);
    }
}
