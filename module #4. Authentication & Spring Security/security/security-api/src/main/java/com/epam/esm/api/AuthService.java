package com.epam.esm.api;

import com.epam.esm.core.payload.request.AuthRequest;
import com.epam.esm.core.payload.request.SignUpRequest;
import com.epam.esm.core.payload.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse signIn(AuthRequest authRequest);

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    /*void signOut();*/
}
