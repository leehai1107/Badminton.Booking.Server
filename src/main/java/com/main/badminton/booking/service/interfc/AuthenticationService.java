package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.SignInRequest;
import com.main.badminton.booking.dto.request.SignUpRequest;
import com.main.badminton.booking.dto.request.UserProfileDTO;
import com.main.badminton.booking.dto.response.JwtAuthenticationResponse;
import com.main.badminton.booking.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;


import java.util.List;

import java.io.IOException;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    //    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    Object getUserInformation(HttpServletRequest request);

    UserProfileDTO updateUserInfo(Integer id, UserProfileDTO userProfileDTO);

   JwtAuthenticationResponse getTokenAndRefreshToken(UserDetails userDetails) throws BadRequestException;

//   String signingoogle(String email);

    String signingoogle();

//    ResponseEntity<?> signingoogle2(String email);

}
