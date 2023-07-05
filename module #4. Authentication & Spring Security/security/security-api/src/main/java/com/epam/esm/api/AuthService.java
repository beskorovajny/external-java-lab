package com.epam.esm.api;

import com.epam.esm.core.payload.request.AuthRequest;
import com.epam.esm.core.payload.request.SignUpRequest;
import com.epam.esm.core.payload.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    AuthenticationResponse signIn(AuthRequest authRequest);

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse refreshToken(String authorizationHeader);

}
