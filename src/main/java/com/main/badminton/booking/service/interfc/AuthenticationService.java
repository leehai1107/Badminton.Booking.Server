package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.RefreshTokenRequest;
import com.main.badminton.booking.dto.request.SignInRequest;
import com.main.badminton.booking.dto.request.SignUpRequest;
import com.main.badminton.booking.dto.response.JwtAuthenticationResponse;
import com.main.badminton.booking.entity.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
