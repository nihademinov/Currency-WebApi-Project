package org.example.exchangeratewebapiproject.service;

import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.bussiness.management.UserManager;
import org.example.exchangeratewebapiproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserManager userManager;

    public List<UserDto> getAllUsers() {
        return userManager.geUsers();
    }

    public UserDto getUserById(Long id) {
        return userManager.getUsersById(id);
    }
    public UserDto updateUser(Long id,UserDto userDto) {
        return userManager.updateUser(id,userDto);
    }
    public String deleteUser(Long id) {
        return userManager.deleteUser(id);
    }

}
