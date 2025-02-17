package com.main.badminton.booking.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.badminton.booking.config.ApplicationAuditing;

import com.main.badminton.booking.dto.request.SignInRequest;
import com.main.badminton.booking.dto.request.SignUpRequest;
import com.main.badminton.booking.dto.request.UserProfileDTO;
import com.main.badminton.booking.dto.response.JwtAuthenticationResponse;
import com.main.badminton.booking.entity.Role;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.RoleRepo;
import com.main.badminton.booking.repository.TokenRepository;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.AuthenticationService;
import com.main.badminton.booking.service.interfc.JWTService;
import com.main.badminton.booking.token.Token;
import com.main.badminton.booking.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final RoleRepo roleRepo;


    @Autowired
    private final TokenRepository tokenRepository;



    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        Role role = roleRepo.findById(signUpRequest.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        user.setRole(role);

        // Nếu vai trò là "staff", thì phải có createBy từ Auditing
        if (3 == role.getId()) {
            ApplicationAuditing auth = new ApplicationAuditing();
            var currentAuditor = auth.getCurrentAuditor();
            if (currentAuditor.isPresent()) {
                user.setCreateBy(currentAuditor.get());
            } else {
                throw new IllegalStateException("No auditor found for staff sign up");
            }
        } else {
            user.setCreateBy(0);
        }

        var savedUser = userRepo.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken, refreshToken);

        return JwtAuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        var user = userRepo.findByUsername(signInRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserToken(user);
        saveUserToken(user, jwt, refreshToken);

        JwtAuthenticationResponse jwtAuthenticationResponse =
                JwtAuthenticationResponse.builder()
                        .token(jwt)
                        .refreshToken(refreshToken)
                        .build();
        return jwtAuthenticationResponse;
    }


    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshedToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("text/plain");
            try {
                response.getWriter().write("No JWT token found in the request header");
            } catch (IOException e) {
//                logger.error("Error writing unauthorized response", e);
                throw new BadRequestException("ERROR !");
            }
            return;
        }
        refreshedToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshedToken);
        final Token currentRefreshedToken = tokenRepository.findByRefreshToken(refreshedToken).get();

        if (username != null) {
            var user = this.userRepo.findByUsername(username).orElseThrow();
            if ((jwtService.isTokenValid(refreshedToken, user)) &&
                    !currentRefreshedToken.isRevoked() && !currentRefreshedToken.isExpired()) {
                var accessToken = jwtService.generateToken(user);
                var authResponse = JwtAuthenticationResponse.builder()
                        .token(accessToken)
                        .refreshToken(refreshedToken)
                        .build();
                revokeAllUserToken(user);
                saveUserToken(user, accessToken, refreshedToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                ResponseEntity.ok(authResponse);
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("text/plain");
                try {
                    response.getWriter().write("JWT token has expired and revoked");
                } catch (IOException e) {
//                    logger.error("Error writing unauthorized response", e);
                    throw new BadRequestException("ERROR");
                }
            }
            return;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("text/plain");
        try {
            response.getWriter().write("Unauthorized");
        } catch (IOException e) {
//            logger.error("Error writing unauthorized response", e);
            throw new BadRequestException("ERROR");
        }
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @Override
    public Object getUserInformation(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No JWT token found in the request header");
        }

        final Token accessToken = tokenRepository.findByToken(token).orElse(null);
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token");
        }

        String username = jwtService.extractUsername(token);
        var user = userRepo.findByUsername(username).orElse(null);
        if (user == null || !jwtService.isTokenValid(token, user) || accessToken.isRevoked() || accessToken.isExpired()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired and revoked");
        }

        return user;
    }

    @Override
    public UserProfileDTO updateUserInfo(Integer id, UserProfileDTO userProfileDTO) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userProfileDTO.getUserName());
            user.setLastName(userProfileDTO.getLastName());
            user.setFirstName(userProfileDTO.getFirstName());
            user.setEmail(userProfileDTO.getEmail());
            user.setDob(userProfileDTO.getDob());
            user.setGender(userProfileDTO.getGender());

            User userResponse = userRepo.save(user);
            return mapToUserProfileDto(userResponse);
        } else {
            return null;
        }
    }

    @Override
    public JwtAuthenticationResponse getTokenAndRefreshToken(UserDetails userDetails) throws BadRequestException {
        JwtAuthenticationResponse res = new JwtAuthenticationResponse();
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("not found"));
            if (user != null) {
                Token token = tokenRepository.findAllValidTokensByUser2((long) user.getId());
                res.setToken(token.getToken());
                res.setRefreshToken(token.getRefreshToken());
            } else {
                throw new BadRequestException("Exception");
            }
            return res;
        } else {
            throw new BadRequestException("Exception");
        }
    }

    @Override
    public String signingoogle() {

//        OAuth2AuthenticationToken auth2AuthenticationToken;
//        try {
//            OAuth2User oAuth2User = this.loadUser(userRequest);
//            String email = oAuth2User.getAttribute("email");
//            Optional<User> userDetails = userRepo.findByEmail(email);
//            User user = new User();
//            if (userDetails.isEmpty()) {
//                user.setEmail(email);
//                user.setRole(roleRepo.findById(2).get());
//                user.setCreateBy(0);
//                user.setUsername(email);
//                userRepo.save(user);
////                user.setEn
//            } else {
//                user = userDetails.get();
//            }
//            var jwtToken = jwtService.generateToken(user);
//            var refreshToken = jwtService.generateRefreshToken(user);
//
//            revokeAllUserToken(user);
//            saveUserToken(user, jwtToken, refreshToken);
//            return jwtToken;
//        } catch (Exception e) {
//            throw new IllegalStateException(e.getMessage());
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        Optional<User> userOptional = userRepo.findByEmail(email);
        User user = userOptional.get();

        List<Token> listToken = tokenRepository.findAllValidTokensByUser((long) user.getId());
        Token token = new Token();
        if(listToken.size() == 1){
            token = listToken.get(0);
        }

        return token.getToken();
    }



    private UserProfileDTO mapToUserProfileDto(User user) {
        UserProfileDTO userProfileDto = new UserProfileDTO();
        userProfileDto.setFirstName(user.getFirstName());
        userProfileDto.setLastName(user.getLastName());
        userProfileDto.setGender(user.getGender());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setDob(user.getDob());
        userProfileDto.setUserName(user.getUsername());
        return userProfileDto;
    }


    private void revokeAllUserToken(User user) {
        var validUserToken = tokenRepository.findAllValidTokensByUser((long) user.getId());
        if (validUserToken.isEmpty()) return;
        validUserToken.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }


    private void saveUserToken(User user, String jwtToken, String jwtRefreshToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .refreshToken(jwtRefreshToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
}
