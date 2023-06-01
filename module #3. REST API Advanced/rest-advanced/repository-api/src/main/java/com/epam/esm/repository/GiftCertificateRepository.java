package com.epam.esm.repository;

import com.epam.esm.core.model.entity.GiftCertificate;
<<<<<<< HEAD
import com.epam.esm.core.model.query.QueryParams;
import org.springframework.data.domain.Pageable;
=======
import com.epam.esm.core.model.pagination.Pageable;
import com.epam.esm.core.model.query.QueryParams;
>>>>>>> module_3

import java.util.List;
import java.util.Set;

public interface GiftCertificateRepository extends GenericRepository<GiftCertificate, Long> {
    List<GiftCertificate> findAllByTags(Set<String> tags, Pageable pageable);

    List<GiftCertificate> findAllByName(String name, Pageable pageable);

    List<GiftCertificate> findAllWithParams(QueryParams queryParams, Pageable pageable);

    List<GiftCertificate> findAllByReceipt(Long receiptID, Pageable pageable);
<<<<<<< HEAD

    Long getTotalRecordsForReceiptID(Long receiptID);
    Long getTotalRecordsForNameLike(String name);
    Long getTotalRecordsForParams(QueryParams queryParams);
    Long getTotalRecordsForTagsParam(Set<String> tagNames);
=======
    Long getTotalRecordsForReceiptID(Long receiptID);
>>>>>>> module_3
}

