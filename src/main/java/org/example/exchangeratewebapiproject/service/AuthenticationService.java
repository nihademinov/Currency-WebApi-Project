package org.example.exchangeratewebapiproject.service;

import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.requestDto.AuthenticationRequestDto;
import org.example.exchangeratewebapiproject.api.dto.requestDto.RegisterRequestDto;
import org.example.exchangeratewebapiproject.api.dto.responseDto.AuthenticationResponse;
import org.example.exchangeratewebapiproject.bussiness.management.AuthManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {



    private final AuthManager authManager;

    public String register(RegisterRequestDto request) {
       return authManager.register(request);
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
      return authManager.authenticate(request);
    }


//    public AuthenticationResponse refreshToken(String refreshToken) {
//       return authManager.refreshToken(refreshToken);
//    }
}

