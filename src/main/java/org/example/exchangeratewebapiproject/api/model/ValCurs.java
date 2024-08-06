package org.example.exchangeratewebapiproject.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ValCurs",schema = "Currency")
public class ValCurs  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ValCursId")
    private Long id;

    @Column(name = "Date")
    @NotBlank
    private String date;

    @Column(name = "Name")
    @NotBlank
    private String name;

    @Column(name = "Description")
    @NotBlank
    private String description;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalTime createdAt;

    @Column(name = "UpdatedAt")
    @UpdateTimestamp
    private LocalTime updatedAt;

    @Column(name = "DeletedAt")
    private LocalTime deletedAt;


    @OneToMany(mappedBy = "valCurs", cascade = {CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ValType> valTypes;

}