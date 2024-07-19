package org.example.exchangeratewebapiproject.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class ValCurs extends  SuperEntity {

    private String date;

    private String name;

    private String description;


    @OneToMany(mappedBy = "valCurs", cascade = {CascadeType.PERSIST}, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<ValType> valTypes;

}