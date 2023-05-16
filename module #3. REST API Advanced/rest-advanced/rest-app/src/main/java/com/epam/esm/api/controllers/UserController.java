package com.epam.esm.api.controllers;

import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.service.ReceiptService;
import com.epam.esm.service.UserService;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ReceiptService receiptService;

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
    @GetMapping("/find/{userID}/receipts")
    List<ReceiptDTO> findReceiptByUser(@PathVariable Long userID) {
        return receiptService.findAllByUser(userID);
    }
}
