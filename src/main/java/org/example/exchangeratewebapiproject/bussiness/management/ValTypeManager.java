package org.example.exchangeratewebapiproject.bussiness.management;

import com.remondis.remap.Mapper;
import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValTypeDto;
import org.example.exchangeratewebapiproject.api.model.ValType;
import org.example.exchangeratewebapiproject.exceptionHandler.NotFoundException;
import org.example.exchangeratewebapiproject.repository.ValTypeRepository;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ValTypeManager {
    private final ValTypeRepository valTypeRepository;
    private final Mapper<ValType, ValTypeDto> valTypeEntityToValTypeDtoMapper;

   public ValTypeDto getValTypeById(Long id) {
      Optional<ValType> valTypes =  valTypeRepository.findById(id);

      if(valTypes.isEmpty())
      {
          throw  new NotFoundException("ValType not found");
      }
      return valTypeEntityToValTypeDtoMapper.map(valTypes.get());
   }
}
