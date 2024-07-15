package org.example.exchangeratewebapiproject.configuration;

import org.example.exchangeratewebapiproject.api.dto.ValCursDto;
import org.example.exchangeratewebapiproject.api.dto.ValTypeDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValCursMapDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValTypeMapDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValuteMapDto;
import org.example.exchangeratewebapiproject.api.model.ValCurs;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.example.exchangeratewebapiproject.api.model.Valute;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure mappings for nested objects
        modelMapper.addMappings(new PropertyMap<ValCurs, ValCursMapDto>() {
            @Override
            protected void configure() {
                map().setDate(source.getDate());
                map().setName(source.getName());
                map().setDescription(source.getDescription());
                // Custom logic for nested list mapping
                using(context -> {
                    List<ValType> valTypes = ((ValCurs) context.getSource()).getValTypes();
                    return valTypes.stream()
                            .map(valType -> modelMapper.map(valType, ValTypeMapDto.class))
                            .collect(Collectors.toList());
                }).map(source, destination.getValTypes());
            }
        });

        modelMapper.addMappings(new PropertyMap<ValType, ValTypeMapDto>() {
            @Override
            protected void configure() {
                map().setType(source.getType());
                // Custom logic for nested list mapping
                using(context -> {
                    List<Valute> valutes = ((ValType) context.getSource()).getValutes();
                    return valutes.stream()
                            .map(valute -> modelMapper.map(valute, ValuteMapDto.class))
                            .collect(Collectors.toList());
                }).map(source, destination.getValutes());
            }
        });

        return modelMapper;
    }
}
