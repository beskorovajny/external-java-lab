package com.epam.esm.repository;

import com.epam.esm.core.model.entity.User;
import com.epam.esm.core.model.pagination.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User, Long> {
    List<User> findAllByName(String name, Pageable pageable);

    Optional<User> findByName(String name);

    Optional<User> findByReceipt(Long receiptID);
}
