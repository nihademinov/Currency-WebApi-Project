package org.example.exchangeratewebapiproject.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValCursDto {

    private String date;
    private String name;
    private String description;
    private ValTypeDto valType;
}
