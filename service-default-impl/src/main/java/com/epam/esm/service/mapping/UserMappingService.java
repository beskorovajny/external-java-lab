package com.epam.esm.service.mapping;

import com.epam.esm.service.MappingService;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserMappingService implements MappingService<User, UserDTO> {
    @Override
    public User mapFromDto(UserDTO dto) {
        User model = new User();
        BeanUtils.copyProperties(dto, model);
        log.debug("[UserMappingService] UserDTO: [{}] converted to User model: [{}]", dto, model);
        return model;
    }

    @Override
    public UserDTO mapToDto(User model) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(model, dto, "receipts", "password");
        log.debug("[UserMappingService] User model: [{}] converted to UserDTO: [{}]", model, dto);
        return dto;
    }
}
