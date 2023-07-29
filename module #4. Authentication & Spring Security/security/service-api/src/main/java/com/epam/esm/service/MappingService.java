package com.epam.esm.service;

public interface MappingService<T, U> {
    T mapFromDto(U dto);

    U mapToDto(T model);
}
