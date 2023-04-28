package com.epam.esm.repository.impl.hibernate;

import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.utils.QueryParams;
import com.epam.esm.repository.utils.QueryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CertificateHibernateRepository implements GiftCertificateRepository {
    private final QueryProvider queryProvider;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean isExists(GiftCertificate object) {
        boolean result;
        try {
            TypedQuery<GiftCertificate> query = entityManager
                    .createQuery(queryProvider.isExists(), GiftCertificate.class);
            query.setParameter("name", object.getName());
            result = Optional.ofNullable(query.getSingleResult()).isPresent();
        } catch (NoResultException e) {
            return false;
        }
        return result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        log.debug("[GiftCertificateHibernateRepository.save()] GiftCertificate with id:[{}] has been saved.",
                giftCertificate.getId());
        return entityManager.merge(giftCertificate);
    }

    @Override
    public List<GiftCertificate> findAllByName(String name) {
        TypedQuery<GiftCertificate> query = entityManager.createQuery(
                queryProvider.findAllByName(), GiftCertificate.class)
                .setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return entityManager.createQuery(queryProvider.findAll(), GiftCertificate.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Long deleteById(Long id) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, id);
        entityManager.remove(giftCertificate);
        return giftCertificate.getId();
    }

    @Override
    public void attachTagToCertificate(Long tagId, Long certificateId) {

    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        return null;
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, id);
        return Optional.ofNullable(giftCertificate);
    }

    @Override
    public List<GiftCertificate> findAllWithParams(QueryParams queryParams) {
        return Collections.emptyList();
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
