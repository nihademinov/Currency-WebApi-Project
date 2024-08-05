package org.example.exchangeratewebapiproject.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.requestDto.AuthenticationRequestDto;
import org.example.exchangeratewebapiproject.api.dto.responseDto.AuthenticationResponse;
import org.example.exchangeratewebapiproject.api.dto.requestDto.RegisterRequestDto;
import org.example.exchangeratewebapiproject.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
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
    public String register(
            @RequestBody RegisterRequestDto request
    ) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}