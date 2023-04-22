package com.epam.esm.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, U> {
    boolean isExists(U object);

    T save(U object);

    Optional<U> findById(T id);

    Optional<List<U>> findAllByName(String name);

    Optional<List<U>> findAll();

    void deleteById(T id);
}

