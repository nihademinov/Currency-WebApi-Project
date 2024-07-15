package org.example.exchangeratewebapiproject.bussiness.management;

import org.example.exchangeratewebapiproject.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminManager {

    private final UserRepository _userRepository;

    public AdminManager(UserRepository userRepository) {
        _userRepository = userRepository;
    }
}
