package com.epam.esm.repository;

import com.epam.esm.core.model.entity.GiftCertificate;
import com.epam.esm.core.model.query.QueryParams;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface GiftCertificateRepository extends GenericRepository<GiftCertificate, Long> {
    List<GiftCertificate> findAllByTags(Set<String> tags, Pageable pageable);

    List<GiftCertificate> findAllByName(String name, Pageable pageable);

    List<GiftCertificate> findAllWithParams(QueryParams queryParams, Pageable pageable);

    List<GiftCertificate> findAllByReceipt(Long receiptID, Pageable pageable);

    Long getTotalRecordsForReceiptID(Long receiptID);

    Long getTotalRecordsForNameLike(String name);

    Long getTotalRecordsForParams(QueryParams queryParams);

    Long getTotalRecordsForTagsParam(Set<String> tagNames);
}

