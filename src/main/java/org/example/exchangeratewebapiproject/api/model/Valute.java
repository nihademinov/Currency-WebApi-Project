package org.example.exchangeratewebapiproject.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Valute extends SuperEntity {

    private String code;

    private String nominal;

    private String name;

    private double value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "val_type_id")
    private ValType valType;


}