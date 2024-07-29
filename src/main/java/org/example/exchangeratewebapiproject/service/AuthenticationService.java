package org.example.exchangeratewebapiproject.service;

import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.AuthenticationRequestDto;
import org.example.exchangeratewebapiproject.api.dto.RegisterRequestDto;
import org.example.exchangeratewebapiproject.api.model.AuthenticationResponse;
import org.example.exchangeratewebapiproject.bussiness.management.AuthManager;
import org.example.exchangeratewebapiproject.repository.RoleRepository;
import org.example.exchangeratewebapiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {



    private final AuthManager authManager;

    public AuthenticationResponse register(RegisterRequestDto request) {
       return authManager.register(request);
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
      return authManager.authenticate(request);
    }


    public AuthenticationResponse refreshToken(String refreshToken) {
       return authManager.refreshToken(refreshToken);
    }
}

