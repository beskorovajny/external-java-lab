package com.epam.esm.repository;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.model.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateRepository extends GenericRepository<Long, GiftCertificate> {
    boolean isExists(GiftCertificateDTO giftCertificate);
    void update(Long id, GiftCertificate giftCertificate);

    Optional<List<GiftCertificate>> findAllByTagId(Long tagId);
    Optional<List<GiftCertificate>> findAllByTagIdAndName(Long tagId, String name);
    Optional<List<GiftCertificate>> findAllByTagIdAndDescription(Long tagId, String description);
    Optional<List<GiftCertificate>> findAllByDescription(String name);
}
