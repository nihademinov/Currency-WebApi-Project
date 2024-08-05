package org.example.exchangeratewebapiproject.api.dto.responseDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @NotBlank
    private String token;

    @NotBlank
    private String refreshToken;
}