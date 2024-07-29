package org.example.exchangeratewebapiproject.bussiness.management.mapper.requestMapper;


import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValTypeMapDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValuteMapDto;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.example.exchangeratewebapiproject.api.model.Valute;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValuteRequestMapper {

    @Bean
    public Mapper<ValuteMapDto, Valute> valuteMapDtoToValuteEntityMapper() {
        return Mapping
                .from(ValuteMapDto.class)
                .to(Valute.class)
                .omitOthers()
                .mapper();
    }
}
