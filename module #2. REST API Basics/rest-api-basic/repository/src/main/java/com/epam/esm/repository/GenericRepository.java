package com.epam.esm.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, U> {
    T save(U u);

    Optional<U> findById(T id);

    Optional<List<U>> findByName(String name);

    Optional<List<U>> findAll();

    void deleteById(T id);
}
