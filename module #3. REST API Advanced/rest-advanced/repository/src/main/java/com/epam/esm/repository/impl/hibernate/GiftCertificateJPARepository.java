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

import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GiftCertificateJPARepository implements GiftCertificateRepository {
    private static final String FIND_ALL_BY_TAGS = "SELECT DISTINCT gc FROM GiftCertificate gc JOIN gc.tags t" +
            " WHERE t.name IN (:tags)";
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
        entityManager.merge(giftCertificate);
        log.debug("[GiftCertificateHibernateRepository.save()] GiftCertificate :[{}] has been saved.",
                giftCertificate);
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> findAllByTags(Set<String> tags) {
        return entityManager.createQuery(FIND_ALL_BY_TAGS, GiftCertificate.class)
                .setParameter("tags", tags)
                .getResultList();
    }

    @Override
    public List<GiftCertificate> findAllByName(String name) {
        TypedQuery<GiftCertificate> query = entityManager.createQuery(
                queryProvider.findAllByName(), GiftCertificate.class)
                .setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, id);
        return Optional.ofNullable(giftCertificate);
    }

    @Override
    public List<GiftCertificate> findAllWithParams(QueryParams queryParams) {
       // TODO implement this method using JPQL


        return entityManager.createQuery(queryProvider.findAllWithParams(), GiftCertificate.class).getResultList();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return entityManager.createQuery(queryProvider.findAll(), GiftCertificate.class)
                .getResultList();
    }

   /* @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        return null;
    }*/

    @Transactional
    @Override
    public Long deleteById(Long id) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, id);
        giftCertificate.getTags().forEach(tag -> tag.getGiftCertificates().remove(giftCertificate));
        entityManager.remove(giftCertificate);
        return giftCertificate.getId();
    }
    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
