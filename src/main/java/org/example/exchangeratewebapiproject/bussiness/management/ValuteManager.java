package org.example.exchangeratewebapiproject.bussiness.management;

import com.remondis.remap.Mapper;
import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValuteDto;
import org.example.exchangeratewebapiproject.api.model.Valute;
import org.example.exchangeratewebapiproject.repository.ValuteRepository;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ValuteManager {
    private final ValuteRepository valuteRepository;
//    private final ModelMapper modelMapper = new ModelMapper();
    private final Mapper<Valute, ValuteDto> valuteEntityToValuteDtoMapper;


    public ValuteDto getValuteByCode(String date, String code) {
        Valute valutes = valuteRepository.findByCodeAndValCursDate(date, code);

        if (valutes !=null) {
//            return modelMapper.map(valutes.getFirst(),ValuteDto.class);
            ValuteDto map = valuteEntityToValuteDtoMapper.map(valutes);
            map.setValTypeId(valutes.getValType().getId());

            return map;
        }
        return null;
    }


    public ValuteDto calculateValute(double nominal, ValuteDto valuteDto) {
        valuteDto.setNominal(Double.toString(nominal / valuteDto.getValue()));
        valuteDto.setName(valuteDto.getName().replaceAll("\\d+", valuteDto.getNominal()));
        valuteDto.setValue(nominal);
        return valuteDto;
    }
}
