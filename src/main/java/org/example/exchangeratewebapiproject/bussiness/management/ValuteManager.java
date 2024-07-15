package org.example.exchangeratewebapiproject.bussiness.management;

import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValuteDto;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.example.exchangeratewebapiproject.api.model.Valute;
import org.example.exchangeratewebapiproject.repository.ValuteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ValuteManager {
    private final ValuteRepository valuteRepository;
    private final ValTypeManager valTypeManager;
    private final ModelMapper modelMapper = new ModelMapper();

    public ValuteDto getValuteByCode(String date,String code) {
        List<Valute> valutes = valuteRepository.findByCodeAndValCursDate(date,code);

        if(!valutes.isEmpty())
        {
            return modelMapper.map(valutes.get(0),ValuteDto.class);
        }
       return null;
    }


    public ValuteDto setValuteByValue(double nominal,ValuteDto valuteDto) {
        valuteDto.setNominal(Double.toString(nominal/valuteDto.getValue()));
        valuteDto.setName(valuteDto.getName().replaceAll("\\d+", valuteDto.getNominal()));
        valuteDto.setValue(nominal);
        return valuteDto;
    }
}
