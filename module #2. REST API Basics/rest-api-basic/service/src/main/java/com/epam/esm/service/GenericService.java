package com.epam.esm.service;

import java.util.List;

public interface GenericService<T, U> {
    void save(T object);

    T findById(U id);

    List<T> findAllByName(String name);

    List<T> findAll();

    void deleteById(U id);
}
