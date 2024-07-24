package org.example.exchangeratewebapiproject.service;

import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.AuthenticationRequestDto;
import org.example.exchangeratewebapiproject.api.dto.RegisterRequestDto;
import org.example.exchangeratewebapiproject.api.model.AuthenticationResponse;
import org.example.exchangeratewebapiproject.api.model.Role;
import org.example.exchangeratewebapiproject.api.model.User;
import org.example.exchangeratewebapiproject.repository.RoleRepository;
import org.example.exchangeratewebapiproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse register(RegisterRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfigPassword(passwordEncoder.encode(user.getConfigPassword()));

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        user.setRoles(List.of(userRole));

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    public AuthenticationResponse refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        if (username != null && jwtService.isTokenValid(refreshToken, userDetailsService.loadUserByUsername(username))) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newToken = jwtService.generateToken(userDetails);
            return AuthenticationResponse.builder()
                    .token(newToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        throw new RuntimeException("Invalid refresh token");
    }
}

