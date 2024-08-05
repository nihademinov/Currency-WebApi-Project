package org.example.exchangeratewebapiproject.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValuteResponseDto {

    @NotBlank
    private String code;

    @NotBlank
    private String nominal;

    @NotBlank
    private String name;

    @NotNull
    private Double value;
}
