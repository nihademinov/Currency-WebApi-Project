package org.example.exchangeratewebapiproject.api.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValTypeDto {

    @NotBlank
    private String type;

    private ValuteResponseDto valute;
}
