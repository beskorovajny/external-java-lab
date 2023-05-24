package com.epam.esm.api.controllers;

import com.epam.esm.api.assembler.ReceiptModelAssembler;
import com.epam.esm.api.assembler.UserModelAssembler;
import com.epam.esm.api.model.ReceiptModel;
import com.epam.esm.api.model.UserModel;
import com.epam.esm.service.ReceiptService;
import com.epam.esm.service.UserService;
import com.epam.esm.core.model.pagination.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ReceiptService receiptService;
    private final UserModelAssembler userModelAssembler;
    private final ReceiptModelAssembler receiptModelAssembler;

    @GetMapping("/find/{id}")
    public UserModel findByID(@PathVariable Long id) {
        return userModelAssembler.toModel(userService.findById(id));
    }

    @GetMapping("/find")
    public List<UserModel> findAllByName(@RequestParam String name,
                             @RequestParam Integer page,
                             @RequestParam Integer pageSize) {
        return userService
                .findAllByName(name, new Pageable(page, pageSize))
                .stream()
                .map(userModelAssembler::toModel)
                .toList();
    }

    @GetMapping("/find-all")
    public List<UserModel> findAll(@RequestParam Integer page,
                          @RequestParam Integer pageSize) {
        return userService
                .findAll(new Pageable(page, pageSize))
                .stream()
                .map(userModelAssembler::toModel)
                .toList();
    }
    @GetMapping("/find/{userID}/receipts")
    public List<ReceiptModel> findReceiptsByUserID(@PathVariable Long userID,
                                         @RequestParam Integer page,
                                         @RequestParam Integer pageSize) {
        return receiptService
                .findAllByUser(userID, new Pageable(page, pageSize))
                .stream()
                .map(receiptModelAssembler::toModel)
                .toList();
    }
}
