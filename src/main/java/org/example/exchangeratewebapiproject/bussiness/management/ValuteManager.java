package org.example.exchangeratewebapiproject.bussiness.management;

import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValuteDto;
import org.example.exchangeratewebapiproject.repository.ValuteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValuteManager {
    private final ValuteRepository valuteRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public ValuteDto getValuteByCode(String code) {
       return modelMapper.map(valuteRepository.findByCode(code), ValuteDto.class);
    }


    public ValuteDto setValuteByValue(double nominal,ValuteDto valuteDto) {
        valuteDto.setNominal(Double.toString(nominal/valuteDto.getValue()));
        valuteDto.setName(valuteDto.getName().replaceAll("\\d+", valuteDto.getNominal()));
        valuteDto.setValue(nominal);
        return valuteDto;
    }
}
