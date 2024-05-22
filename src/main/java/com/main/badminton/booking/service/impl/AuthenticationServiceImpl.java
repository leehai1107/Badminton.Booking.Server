package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.dto.request.RefreshTokenRequest;
import com.main.badminton.booking.dto.request.SignInRequest;
import com.main.badminton.booking.dto.request.SignUpRequest;
import com.main.badminton.booking.dto.response.JwtAuthenticationResponse;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.RoleRepo;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.AuthenticationService;
import com.main.badminton.booking.service.interfc.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final RoleRepo roleRepo;



    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(roleRepo.getById(2));
        return userRepo.save(user);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
       var user = userRepo.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
       var jwt = jwtService.generateToken(user);
       var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

       JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
       jwtAuthenticationResponse.setToken(jwt);
       jwtAuthenticationResponse.setRefreshToken(refreshToken);
       return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepo.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
