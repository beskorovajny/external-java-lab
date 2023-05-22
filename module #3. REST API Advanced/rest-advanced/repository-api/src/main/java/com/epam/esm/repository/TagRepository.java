package com.epam.esm.repository;

import com.epam.esm.core.model.entity.Tag;
import com.epam.esm.core.model.pagination.Pageable;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    List<Tag> findAllByCertificate(Long certificateID, Pageable pageable);

    Optional<Tag> findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();
    Long getTotalRecordsForGiftCertificateID(Long giftCertificateID);
}
