package org.example.exchangeratewebapiproject.bussiness.management.mapper.requestMapper;


import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValCursMapDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValTypeMapDto;
import org.example.exchangeratewebapiproject.api.model.ValCurs;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValCursRequestMapper {

    private final Mapper<ValTypeMapDto, ValType> valTypeMapDtoToValTypeEntityMapper;

    @Bean
    public Mapper<ValCursMapDto, ValCurs> valCursMapDtoToValCursMapper() {
        return Mapping
                .from(ValCursMapDto.class)
                .to(ValCurs.class)
                .useMapper(valTypeMapDtoToValTypeEntityMapper)
                .omitOthers()
                .mapper();
    }
}
