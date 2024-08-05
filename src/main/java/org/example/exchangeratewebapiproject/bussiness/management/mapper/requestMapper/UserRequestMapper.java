package org.example.exchangeratewebapiproject.bussiness.management.mapper.requestMapper;


import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.requestDto.AuthenticationRequestDto;
import org.example.exchangeratewebapiproject.api.dto.requestDto.RegisterRequestDto;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.api.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestMapper {


    @Bean
    public Mapper<RegisterRequestDto, User> userRegisterDtoToEntityMapper() {
        return Mapping
                .from(RegisterRequestDto.class)
                .to(User.class)
                .omitOthers()
                .mapper();
    }

    @Bean
    public Mapper<AuthenticationRequestDto, User> userAuthenticationDtoToEntityMapper() {
        return Mapping
                .from(AuthenticationRequestDto.class)
                .to(User.class)
                .omitOthers()
                .mapper();
    }

    @Bean
    public Mapper<UserDto, User> userDtoToEntityMapper() {
        return Mapping
                .from(UserDto.class)
                .to(User.class)
                .omitOthers()
                .mapper();
    }


}
