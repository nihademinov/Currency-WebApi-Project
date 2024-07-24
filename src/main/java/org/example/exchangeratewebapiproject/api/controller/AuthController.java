package org.example.exchangeratewebapiproject.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.AuthenticationRequestDto;
import org.example.exchangeratewebapiproject.api.dto.RefreshTokenRequestDto;
import org.example.exchangeratewebapiproject.api.dto.RegisterRequestDto;
import org.example.exchangeratewebapiproject.api.model.AuthenticationResponse;
import org.example.exchangeratewebapiproject.service.AuthenticationService;
import org.example.exchangeratewebapiproject.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}