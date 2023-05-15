package com.epam.esm.repository;

import com.epam.esm.core.model.Pageable;
import com.epam.esm.core.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User, Long> {
    List<User> findAllByName(String name, Pageable pageable);

    Optional<User> findByName(String name);
}
