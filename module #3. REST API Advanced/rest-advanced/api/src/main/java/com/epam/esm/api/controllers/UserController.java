package com.epam.esm.api.controllers;

import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.repository.utils.Pageable;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/find/{id}")
    UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/find")
    List<UserDTO> findByName(@RequestParam String name,
                             @RequestParam Integer page,
                             @RequestParam Integer pageSize) {
        return userService.findAllByName(name, new Pageable(page, pageSize));
    }

    @GetMapping("/find-all")
    List<UserDTO> findAll(@RequestParam Integer page,
                            @RequestParam Integer pageSize) {
        return userService.findAll(new Pageable(page, pageSize));
    }
}
