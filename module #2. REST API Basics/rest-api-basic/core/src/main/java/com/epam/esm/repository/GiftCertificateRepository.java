package com.epam.esm.repository;

import com.epam.esm.model.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateRepository {
    boolean isExists(GiftCertificate giftCertificate);
    Long save(GiftCertificate giftCertificate);
    Optional<GiftCertificate> findById(Long id);
    Optional<GiftCertificate> findByName(String name);
    Optional<List<GiftCertificate>> findAll();
    void update(Long id, GiftCertificate giftCertificate);
    void deleteById(Long id);
}
