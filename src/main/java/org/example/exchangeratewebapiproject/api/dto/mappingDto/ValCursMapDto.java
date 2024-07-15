package org.example.exchangeratewebapiproject.api.dto.mappingDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.exchangeratewebapiproject.api.model.ValType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)

public class ValCursMapDto {

    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "Name")
    private String name;

    @XmlAttribute(name = "Description")
    private String description;

    @XmlElement(name = "ValType")
    private List<ValTypeMapDto> valTypes;
}
