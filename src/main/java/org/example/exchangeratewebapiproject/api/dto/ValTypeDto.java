package org.example.exchangeratewebapiproject.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValTypeDto {

    private String type;
    private ValuteResponseDto valute;
}
