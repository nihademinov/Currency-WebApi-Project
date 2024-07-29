package org.example.exchangeratewebapiproject.bussiness.management.mapper.responseMapper;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.api.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResponseMapper {


    @Bean
    public Mapper<User, UserDto> entityToUserDtoMapper() {
        return Mapping
                .from(User.class)
                .to(UserDto.class)
                .omitOthers()
                .mapper();
    }
}
