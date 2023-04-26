package com.epam.esm.repository.impl.hibernate;

import com.epam.esm.core.model.Tag;
import com.epam.esm.repository.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Getter
public class TagHibernateRepository implements TagRepository {
    private static final String FIND_SINGLE_BY_NAME = "SELECT t FROM Tag t WHERE t.name= :name";
    private static final String FIND_ALL_BY_NAME = "SELECT t FROM Tag t WHERE LOWER(t.name) LIKE LOWER(:name)";
    private static final String FIND_ALL = "SELECT t FROM Tag t";

    private static final String FIND_ALL_BY_CERTIFICATE = "SELECT t FROM Tag t LEFT JOIN" +
            " t.giftCertificates c WHERE c.id = :id";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isExists(Tag object) {
        boolean result;
        try {
            result = findByName(object.getName()).isPresent();
        } catch (NoResultException e) {
            return false;
        }
        return result;
    }

    @Transactional
    @Override
    public Tag save(Tag tag) {
        entityManager.persist(tag);
        log.debug("[TagHibernateRepository.save()] Tag with id:[{}] has been saved.", tag.getId());
        return tag;
    }

    @Override
    public Optional<Tag> findById(Long id) {
        Tag tag = entityManager.find(Tag.class, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        TypedQuery<Tag> query = entityManager.createQuery(FIND_SINGLE_BY_NAME, Tag.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<List<Tag>> findAllByName(String name) {
        TypedQuery<Tag> query = entityManager.createQuery(
                FIND_ALL_BY_NAME, Tag.class);
        query.setParameter("name", "%" + name + "%");
        return Optional.of(query.getResultList());
    }

    @Override
    public Optional<List<Tag>> findAll() {
        return Optional.of(entityManager.createQuery(FIND_ALL, Tag.class)
                .getResultList());
    }

    @Override
    public Optional<List<Tag>> findAllByCertificate(Long certificateId) {
        TypedQuery<Tag> query = entityManager.createQuery(
                FIND_ALL_BY_CERTIFICATE, Tag.class);
        query.setParameter("id",certificateId);
        return Optional.of(query.getResultList());
    }

    @Transactional
    @Override
    public Long deleteById(Long id) {
        Tag tag;
        Optional<Tag> tagOptional = findById(id);
        if (tagOptional.isPresent()) {
            tag = tagOptional.get();
            tag.getGiftCertificates().forEach((giftCertificate) -> {
                giftCertificate.getTags().remove(tag);
            });
            log.debug("Tag for removal {}", tag);
            entityManager.remove(tag);
            flushAndClear();
            return tag.getId();
        }
        return 0L;
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
