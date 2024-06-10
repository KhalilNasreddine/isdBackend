package com.book.verse.auth;

import com.book.verse.security.JwtService;
import com.book.verse.role.*;
import com.book.verse.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public void register(RegistrationRequest request){
//        var userRole=roleRepository.findByName("USER")
//                .orElseThrow(()->new RuntimeException("User not found"));
//        var user= User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .accountLocked(false)
//                .enabled(true)
//                .roles(List.of(userRole))
//                .build();
//        userRepository.save(user);
//    }
public AuthenticationResponse register(RegistrationRequest request) {
    var userRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("User role not found"));

    var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .profileImage(request.getProfileimage())
            .password(passwordEncoder.encode(request.getPassword()))
            .accountLocked(false)
            .enabled(true)
            .roles(List.of(userRole))
            .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
}




    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var auth=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var claims=new HashMap<String, Object>();
        var user=(User)auth.getPrincipal();
        claims.put("fullname",user.getFullName());
        var jwtToken=jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .profileimage(user.getProfileImage())
                .build();
    }


}
