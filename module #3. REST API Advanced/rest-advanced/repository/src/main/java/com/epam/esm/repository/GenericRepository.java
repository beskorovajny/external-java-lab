package com.epam.esm.repository;

import java.util.List;
import java.util.Optional;
public interface GenericRepository<T, ID> {
    boolean isExists(T object);

    T save(T object);

    Optional<T> findById(ID id);

    List<T> findAllByName(String name);

    List<T> findAll();

    ID deleteById(ID id);
}

