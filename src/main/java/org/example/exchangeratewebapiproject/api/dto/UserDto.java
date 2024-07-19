package org.example.exchangeratewebapiproject.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class UserDto {

    private String email;
    private String firstName;
    private String lastName;



}