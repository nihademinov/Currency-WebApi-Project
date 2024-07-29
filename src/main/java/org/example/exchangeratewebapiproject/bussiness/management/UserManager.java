package org.example.exchangeratewebapiproject.bussiness.management;

import com.remondis.remap.Mapper;
import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.api.model.User;
import org.example.exchangeratewebapiproject.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    private final Mapper<User, UserDto> entityToUserDtoMapper;
    private final Mapper<UserDto, User> userDtoToUserMapper;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserDto> geUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(entityToUserDtoMapper::map)
                .collect(Collectors.toList());
    }

    public UserDto getUsersById(Long id) {
        Optional<User> resp = userRepository.getUserById(id);
        return resp.stream().map(entityToUserDtoMapper::map).findFirst().orElse(null);
    }

    public UserDto updateUser(Long id, UserDto userDTO) {

        Optional<User> users = userRepository.getUserById(id);

        User user = users.get();
        userDtoToUserMapper.map(userDTO, user);

        saveUser(user);
        return entityToUserDtoMapper.map(user);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

}
