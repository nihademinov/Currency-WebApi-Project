package org.example.exchangeratewebapiproject.api.model;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
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

public class ValType extends SuperEntity {

    private String type;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "val_curs_id")
    private ValCurs valCurs;

    @OneToMany(mappedBy = "valType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Valute> valutes;

}

