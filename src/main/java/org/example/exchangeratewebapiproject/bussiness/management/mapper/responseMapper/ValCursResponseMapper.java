package org.example.exchangeratewebapiproject.bussiness.management.mapper.responseMapper;


import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.api.dto.ValCursDto;
import org.example.exchangeratewebapiproject.api.dto.ValTypeDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValCursMapDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValTypeMapDto;
import org.example.exchangeratewebapiproject.api.model.User;
import org.example.exchangeratewebapiproject.api.model.ValCurs;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValCursResponseMapper {

    private final Mapper<ValType, ValTypeMapDto> valTypeMapper;
    private final Mapper<ValType, ValTypeDto> valTypeEntityToValTypeDtoMapper;

    @Bean
    public Mapper<ValCurs, ValCursMapDto> valCursEntityToValCursMapDtoMapper() {
        return Mapping
                .from(ValCurs.class)
                .to(ValCursMapDto.class)
                .omitOthers()
                .useMapper(valTypeMapper)
                .mapper();
    }

    @Bean
    public Mapper<ValCurs,ValCursDto> valCursToValCursDtoMapper() {
        return Mapping
                .from(ValCurs.class)
                .to(ValCursDto.class)
                .omitOthers()
                .useMapper(valTypeEntityToValTypeDtoMapper)
                .mapper();
    }
}
