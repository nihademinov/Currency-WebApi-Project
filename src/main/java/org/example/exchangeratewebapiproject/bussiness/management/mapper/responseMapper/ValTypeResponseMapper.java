package org.example.exchangeratewebapiproject.bussiness.management.mapper.responseMapper;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValTypeDto;
import org.example.exchangeratewebapiproject.api.dto.ValuteDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValTypeMapDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValuteMapDto;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.example.exchangeratewebapiproject.api.model.Valute;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValTypeResponseMapper {

    private final Mapper<Valute, ValuteMapDto> valuteEntityToValuteMapDtoMapper;
    private final Mapper<Valute, ValuteDto> valuteEntityToValuteDtoMapper;

    @Bean
    public Mapper<ValType, ValTypeDto> valTypeEntityToValTypeDtoMapper() {
        return Mapping
                .from(ValType.class)
                .to(ValTypeDto.class)
                .omitOthers()
                .useMapper(valuteEntityToValuteDtoMapper)
                .mapper();
    }

    @Bean
    public Mapper<ValType, ValTypeMapDto> valTypeMapper() {
        return Mapping
                .from(ValType.class)
                .to(ValTypeMapDto.class)
                .omitOthers()
                .useMapper(valuteEntityToValuteMapDtoMapper)
                .mapper();
    }
}
