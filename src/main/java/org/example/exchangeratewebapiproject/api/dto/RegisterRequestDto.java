package org.example.exchangeratewebapiproject.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor




public class RegisterRequestDto {

    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private String configPassword;
    private List<String> roles;

}
