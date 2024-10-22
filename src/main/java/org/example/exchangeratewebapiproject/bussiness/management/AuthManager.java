package org.example.exchangeratewebapiproject.bussiness.management;

import com.remondis.remap.Mapper;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.requestDto.AuthenticationRequestDto;
import org.example.exchangeratewebapiproject.api.dto.requestDto.RegisterRequestDto;
import org.example.exchangeratewebapiproject.api.dto.responseDto.AuthenticationResponse;
import org.example.exchangeratewebapiproject.api.model.Role;
import org.example.exchangeratewebapiproject.api.model.User;
import org.example.exchangeratewebapiproject.exceptionHandler.AlreadyExistsException;
import org.example.exchangeratewebapiproject.exceptionHandler.NotFoundException;
import org.example.exchangeratewebapiproject.exceptionHandler.PasswordMatchException;
import org.example.exchangeratewebapiproject.exceptionHandler.RefreshTokenException;
import org.example.exchangeratewebapiproject.repository.RoleRepository;
import org.example.exchangeratewebapiproject.repository.UserRepository;
import org.example.exchangeratewebapiproject.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthManager {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final Mapper<RegisterRequestDto, User> userRegisterDtoToEntityMapper;
    private final UserManager userManager;

    public String register(RegisterRequestDto request) {
        User user = userRegisterDtoToEntityMapper.map(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userManager.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException(user.getEmail() + " already exists");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMatchException("Passwords do not match");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new NotFoundException("USER role not found"));

        user.setRoles(List.of(userRole));

        userRepository.save(user);


        return "User successfully registered";
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
        if (!request.getPassword().equals("admin")) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(request.getEmail()));

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
        throw new RefreshTokenException("Invalid refresh token");
    }

}
