package com.epam.esm.repository;

import com.epam.esm.core.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

    List<Tag> findAllByCertificate(Long certificateID);
    Optional<Tag> findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();
}
