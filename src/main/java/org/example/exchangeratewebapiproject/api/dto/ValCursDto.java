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
public class ValCursDto {

    @NotBlank
    private String date;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private ValTypeDto valType;
}
