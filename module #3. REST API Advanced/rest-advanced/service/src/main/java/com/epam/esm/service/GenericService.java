package com.epam.esm.service;

import java.util.List;

public interface GenericService<T, U> {
    T save(T object);

    T findById(U id);

    List<T> findAll();

    void deleteById(U id);
}
