package org.example.exchangeratewebapiproject.bussiness.management;

import com.remondis.remap.Mapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValCursDto;
import org.example.exchangeratewebapiproject.api.dto.ValuteDto;
import org.example.exchangeratewebapiproject.api.dto.ValuteResponseDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValCursMapDto;
import org.example.exchangeratewebapiproject.api.model.ValCurs;
import org.example.exchangeratewebapiproject.configuration.RestTemplateConfig;
import org.example.exchangeratewebapiproject.exceptionHandler.CustomRestClientException;
import org.example.exchangeratewebapiproject.repository.ValCursRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ValCursManager {
    private final ValCursRepository valCursRepository;
    private final ValuteManager valuteManager;
    private final ValTypeManager valTypeManager;
    //    private final ModelMapper modelMapper = new ModelMapper();
    private final Mapper<ValCurs, ValCursMapDto> valCursEntityToValCursMapDtoMapper;
    private final Mapper<ValCursMapDto, ValCurs> valCursMapDtoToValCursMapper;
    private final RestTemplateConfig restTemplateConfig;
    private final Mapper<ValuteDto, ValuteResponseDto> valuteDtoToValuteResponseDtoMapper;
    private final Mapper<ValCurs,ValCursDto> valCursToValCursDtoMapper;

    public List<ValCursMapDto> getAllValCurs() {
        return valCursRepository.findAll().stream()
                .map(valCursEntityToValCursMapDtoMapper::map)
                .collect(Collectors.toList());

    }

    public ValCursMapDto getValCursByDate(LocalDate date) {
        return valCursEntityToValCursMapDtoMapper.map(valCursRepository.getValCursByDate(dateConvertor(date)));
    }

    public boolean checkValCursByDate(LocalDate date) {
        return valCursRepository.existsByDate(dateConvertor(date));
    }

    public void saveValCurs(ValCurs valCurs) {
        valCursRepository.save(valCurs);
    }

    public void createValCurs(ValCursMapDto valCurs) {
        ValCurs result = valCursMapDtoToValCursMapper.map(valCurs);
        result.getValTypes().forEach(type -> {
            type.setValCurs(result);
            type.getValutes().forEach(valute -> valute.setValType(type));
        });
        saveValCurs(result);
    }

    public ValCursMapDto getValCursMapDto(LocalDate date) {
        try {
            String formattedDate = dateConvertor(date);
            String url = "https://www.cbar.az/currencies/" + formattedDate + ".xml";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplateConfig.getRestTemplate().exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() != HttpStatus.OK)
                throw new CustomRestClientException("HTTP error: " + response.getStatusCodeValue());

            MediaType contentType = response.getHeaders().getContentType();
            if (!MediaType.APPLICATION_XML.includes(contentType))
                throw new CustomRestClientException("Unexpected content type: " + contentType);

            String xmlData = response.getBody();
            JAXBContext context = JAXBContext.newInstance(ValCursMapDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            return (ValCursMapDto) unmarshaller.unmarshal(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ValCursDto createResponseBySpecificValCurs(String date, ValuteDto valuteDto) {
        ValCurs valcurs = valCursRepository.getValCursByDate(date);
        ValCursDto result = new ValCursDto();
        result.setValType(valTypeManager.getValTypeById(valuteDto.getValTypeId()));
        result.getValType().setValute(valuteDtoToValuteResponseDtoMapper.map(valuteDto));
        result.setDate(date);
        result.setDescription(valcurs.getDescription());
        result.setName(valcurs.getName());

        return result;
    }


    public ValCursDto getValCursByValute(LocalDate date, double nominal, String valuteCode) {
        if (checkValCursByDate(date)) {

            ValuteDto valute = valuteManager.getValuteByCode(dateConvertor(date), valuteCode);

            valute = valuteManager.calculateValute(nominal, valute);
            return createResponseBySpecificValCurs(dateConvertor(date), valute);
        }
        return null;
    }

    public void deleteValCursByDate(LocalDate date) {
        valCursRepository.deleteByDate(dateConvertor(date));
    }

    private String dateConvertor(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }
}
