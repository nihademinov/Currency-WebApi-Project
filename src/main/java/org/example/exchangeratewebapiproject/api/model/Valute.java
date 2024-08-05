package org.example.exchangeratewebapiproject.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Valute",schema = "Currency")
public class Valute  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ValuteId")
    private Long id;

    @Column(name = "Code")
    private String code;

    @Column(name = "Nominal")
    private String nominal;

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private double value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ValTypeId")
    private ValType valType;


    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalTime createdAt;

    @Column(name = "UpdatedAt")
    @UpdateTimestamp
    private LocalTime updatedAt;

    @Column(name = "DeletedAt")
    private LocalTime deletedAt;

}