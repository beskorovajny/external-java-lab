package com.epam.esm.repository;

import com.epam.esm.core.model.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User, Long> {
    List<User> findAllByName(String name, Pageable pageable);

    Optional<User> findByReceipt(Long receiptID);

    Long getTotalRecordsForNameLike(String name);
}
