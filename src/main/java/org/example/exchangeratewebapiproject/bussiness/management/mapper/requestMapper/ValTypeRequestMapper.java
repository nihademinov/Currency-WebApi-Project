package org.example.exchangeratewebapiproject.bussiness.management.mapper.requestMapper;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValTypeMapDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValuteMapDto;
import org.example.exchangeratewebapiproject.api.model.User;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.example.exchangeratewebapiproject.api.model.Valute;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValTypeRequestMapper {

    private final Mapper<ValuteMapDto, Valute> valuteMapDtoToValuteEntityMapper;

    @Bean
    public Mapper<ValTypeMapDto, ValType> valTypeMapDtoToValTypeEntityMapper() {
        return Mapping
                .from(ValTypeMapDto.class)
                .to(ValType.class)
                .omitOthers()
                .useMapper(valuteMapDtoToValuteEntityMapper)
                .mapper();
    }
}
