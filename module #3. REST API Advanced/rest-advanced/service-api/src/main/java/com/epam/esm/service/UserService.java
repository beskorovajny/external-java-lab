package com.epam.esm.service;

import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.pagination.Pageable;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id);
    List<UserDTO> findAll(Pageable pageable);
    UserDTO findByName(String name);
    List<UserDTO> findAllByName(String name, Pageable pageable);
    UserDTO findByReceipt(Long receiptID);
}
