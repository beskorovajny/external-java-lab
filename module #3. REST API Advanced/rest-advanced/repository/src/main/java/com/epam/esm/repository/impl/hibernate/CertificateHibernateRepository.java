package com.epam.esm.repository.impl.hibernate;

import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.utils.QueryParams;
import com.epam.esm.repository.utils.QueryProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CertificateHibernateRepository implements GiftCertificateRepository {
    private final QueryProvider queryProvider;

    @Override
    public boolean isExists(GiftCertificate object) {
        return false;
    }

    @Override
    public Long save(GiftCertificate object) {
        return null;
    }

    @Override
    public Optional<List<GiftCertificate>> findAllByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<GiftCertificate>> findAll() {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void attachTagToCertificate(Long tagId, Long certificateId) {

    }

    @Override
    public void update(GiftCertificate giftCertificate) {

    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<GiftCertificate>> findAllWithParams(QueryParams queryParams) {
        return Optional.empty();
    }
}
