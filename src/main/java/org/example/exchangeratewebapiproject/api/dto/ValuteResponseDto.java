package org.example.exchangeratewebapiproject.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValuteResponseDto {
    private String code;
    private String nominal;
    private String name;
    private Double value;
}
