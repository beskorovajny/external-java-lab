package com.epam.esm.repository;

import java.util.List;
import java.util.Optional;
public interface GenericRepository<T, U> {
    boolean isExists(T object);

    T save(T object);

    Optional<T> findById(U id);

    List<T> findAllByName(String name);

    List<T> findAll();

    U deleteById(U id);
}

