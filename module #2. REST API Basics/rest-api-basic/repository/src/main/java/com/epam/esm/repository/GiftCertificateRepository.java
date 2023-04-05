package com.epam.esm.repository;

import com.epam.esm.model.GiftCertificate;

public interface GiftCertificateRepository extends GenericRepository<Long, GiftCertificate> {
    boolean isExists(GiftCertificate giftCertificate);
    void update(Long id, GiftCertificate giftCertificate);
}
