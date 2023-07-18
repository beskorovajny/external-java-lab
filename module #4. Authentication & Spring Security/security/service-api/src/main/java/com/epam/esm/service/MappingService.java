package com.epam.esm.service;

import org.springframework.stereotype.Service;

public interface MappingService<T, U> {
    T mapFromDto(U dto);

    U mapToDto(T model);
}
