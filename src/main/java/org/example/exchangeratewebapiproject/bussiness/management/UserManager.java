package org.example.exchangeratewebapiproject.bussiness.management;

import com.remondis.remap.Mapper;
import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.api.model.User;
import org.example.exchangeratewebapiproject.exceptionHandler.NotFoundException;
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

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserDto> geUsers() {
        List<User> allUsers = userRepository.findAll();
        if(allUsers.isEmpty()) {
            throw new NotFoundException("Users not found");
        }
        return allUsers.stream()
                .map(entityToUserDtoMapper::map)
                .collect(Collectors.toList());
    }

    public UserDto getUsersById(Long id) {
        return entityToUserDtoMapper.map(getUser(id));
    }

    public String updateUser(Long id, UserDto userDTO) {
        User user = getUser(id);

        userDtoToUserMapper.map(userDTO, user);
        saveUser(user);
        return "User updated";
    }


    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

}
