package org.example.exchangeratewebapiproject.api.controller;

import lombok.AllArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.UserDto;
import org.example.exchangeratewebapiproject.service.AdminService;
import org.example.exchangeratewebapiproject.service.ValCursService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/currency/admin")
public class AdminController {

    private final ValCursService valCursService;
    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{date}")
    public String deleteValCursByDate(@PathVariable @DateTimeFormat(pattern = "dd.MM.yy") LocalDate date) {
        return valCursService.deleteValCursByDate(date);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<UserDto> getUsers() {
        return adminService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return adminService.updateUser(id, userDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return adminService.deleteUser(id);
    }

}
