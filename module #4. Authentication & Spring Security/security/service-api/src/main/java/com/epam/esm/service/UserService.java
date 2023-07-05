package com.epam.esm.service;

import com.epam.esm.core.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO save(UserDTO userDTO);

    UserDTO findById(Long id);

    UserDTO findByEmail(String eMail);

    Page<UserDTO> findAll(Pageable pageable);

    Page<UserDTO> findAllByName(String name, Pageable pageable);

    UserDTO findByReceipt(Long receiptID);
}
