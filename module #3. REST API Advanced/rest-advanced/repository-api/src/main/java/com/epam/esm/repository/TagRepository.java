package com.epam.esm.repository;

import com.epam.esm.core.model.entity.Tag;
<<<<<<< HEAD
import org.springframework.data.domain.Pageable;
=======
import com.epam.esm.core.model.pagination.Pageable;
>>>>>>> module_3

import java.util.List;
import java.util.Optional;

public interface TagRepository extends GenericRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    List<Tag> findAllByCertificate(Long certificateID, Pageable pageable);

    Optional<Tag> findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();
<<<<<<< HEAD

=======
>>>>>>> module_3
    Long getTotalRecordsForGiftCertificateID(Long giftCertificateID);
}
