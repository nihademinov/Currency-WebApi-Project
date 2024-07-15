package org.example.exchangeratewebapiproject.bussiness.management;

import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.api.model.User;
import org.example.exchangeratewebapiproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserDto> geUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUsersById(Long id)
    {
        Optional<User> resp = userRepository.getUserById(id);
        return resp.stream().map(user -> modelMapper.map(user, UserDto.class)).findFirst().orElse(null);
    }

    public UserDto updateUser(Long id, UserDto userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        Optional<User> users = userRepository.getUserById(id);


        User user = users.get();
        modelMapper.map(userDTO, user);

        saveUser(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

}
