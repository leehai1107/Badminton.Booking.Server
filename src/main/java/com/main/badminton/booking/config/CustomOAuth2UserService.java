//package com.main.badminton.booking.config;
//
//import com.main.badminton.booking.entity.Role;
//import com.main.badminton.booking.entity.User;
//import com.main.badminton.booking.repository.RoleRepo;
//import com.main.badminton.booking.repository.TokenRepository;
//import com.main.badminton.booking.repository.UserRepo;
//import com.main.badminton.booking.service.impl.OAuth2UserDetailCustom;
//import com.main.badminton.booking.service.interfc.JWTService;
//import com.main.badminton.booking.token.Token;
//import com.main.badminton.booking.token.TokenType;
//import java.util.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    @Autowired
//    private UserRepo userRepository;
//    @Autowired
//    private JWTService jwtService;
//    @Autowired
//    private TokenRepository tokenRepository;
//    @Autowired
//    private RoleRepo roleRepo;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        String email = oAuth2User.getAttribute("email");
//        Optional<User> userOptional = userRepository.findByEmail(email);
//
//        User user;
//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//        } else {
//            user = new User();
//            user.setEmail(email);
//            user.setCreateBy(0);
//            user.setUsername(email);
//            user.setFirstName(oAuth2User.getAttribute("given_name"));
//            user.setLastName(oAuth2User.getAttribute("family_name"));
//            user.setPassword(""); // Không cần mật khẩu cho người dùng OAuth2
//            user.setRole(defaultRole()); // Thiết lập vai trò mặc định cho người dùng OAuth2
//            userRepository.save(user);
//        }
//
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//
//        revokeAllUserToken(user);
//        saveUserToken(user, jwtToken, refreshToken);
//
//        OAuth2UserDetailCustom customUser = OAuth2UserDetailCustom.builder()
//                .id(user.getId())
//                .role(user.getRole())
//                .password(user.getPassword())
//                .username(user.getUsername())
//                .attributes(oAuth2User.getAttributes())
//                .build();
//        System.out.println(customUser.getAuthorities());
////        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
////
////        Map<String, GrantedAuthority> authorityMap = authorities.stream()
////                .collect(Collectors.toMap(
////                        GrantedAuthority::getAuthority,
////                        authority -> authority
////                ));
////
////        return new DefaultOAuth2User(
////                authorities,
////                oAuth2User.getAttributes(), // Sử dụng thuộc tính attributes từ OAuth2User
////                "email");
//        return customUser;
//    }
//
//    private Role defaultRole() {
//        return roleRepo.findById(2).orElseThrow(() -> new RuntimeException("Role not found"));
//    }
//
//    private void revokeAllUserToken(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokensByUser((long) user.getId());
//        if (validUserTokens.isEmpty()) return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }
//
//    private void saveUserToken(User user, String jwtToken, String jwtRefreshToken) {
//        Token token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .refreshToken(jwtRefreshToken)
//                .tokenType(TokenType.BEARER)
//                .revoked(false)
//                .expired(false)
//                .build();
//        tokenRepository.save(token);
//    }
//}
