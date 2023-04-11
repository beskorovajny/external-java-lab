package com.epam.esm.repository;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.repository.util.QueryParams;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateRepository extends GenericRepository<Long, GiftCertificate> {
    public void attachTagToCertificate(Long tagId, Long certificateId);
    boolean isExists(GiftCertificate giftCertificate);
    void update(Long id, GiftCertificate giftCertificate);
    Optional<List<GiftCertificate>> findAllWithParams(QueryParams queryParams);
}
