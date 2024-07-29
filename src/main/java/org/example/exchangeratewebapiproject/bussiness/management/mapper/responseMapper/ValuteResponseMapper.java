package org.example.exchangeratewebapiproject.bussiness.management.mapper.responseMapper;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValuteDto;
import org.example.exchangeratewebapiproject.api.dto.ValuteResponseDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValuteMapDto;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.example.exchangeratewebapiproject.api.model.Valute;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValuteResponseMapper {

    @Bean
    public Mapper<ValuteDto, ValuteResponseDto> valuteDtoToValuteResponseDtoMapper() {
        return Mapping
                .from(ValuteDto.class)
                .to(ValuteResponseDto.class)
                .omitOthers()
                .mapper();
    }

    @Bean
    public Mapper<Valute, ValuteDto> valuteEntityToValuteDtoMapper() {
        return Mapping
                .from(Valute.class)
                .to(ValuteDto.class)
//                .replace(Valute::getValType, ValuteDto::getValTypeId)
//                .with(ValType::getId)
                .omitOthers()
                .mapper();
    }


    @Bean
    public Mapper<Valute, ValuteMapDto> valuteEntityToValuteMapDtoMapper() {
        return Mapping
                .from(Valute.class)
                .to(ValuteMapDto.class)
                .omitOthers()
                .mapper();
    }
}
