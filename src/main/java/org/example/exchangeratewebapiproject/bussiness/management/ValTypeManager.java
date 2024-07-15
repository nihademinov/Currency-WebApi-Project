package org.example.exchangeratewebapiproject.bussiness.management;

import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValTypeDto;
import org.example.exchangeratewebapiproject.repository.ValTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValTypeManager {
    private final ValTypeRepository valTypeRepository;
    private final ModelMapper modelMapper = new ModelMapper();

   public ValTypeDto getValTypeById(Long id) {
       return modelMapper.map(valTypeRepository.findById(id), ValTypeDto.class);
   }
}
