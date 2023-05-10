package com.epam.esm.repository;

import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.repository.utils.Pageable;
import com.epam.esm.repository.utils.QueryParams;

import java.util.List;
import java.util.Set;

public interface GiftCertificateRepository extends GenericRepository<GiftCertificate, Long> {
    List<GiftCertificate> findAllByTags(Set<String> tags, Pageable pageable);
    List<GiftCertificate> findAllByName(String name, Pageable pageable);
    List<GiftCertificate> findAllWithParams(QueryParams queryParams, Pageable pageable);
}
