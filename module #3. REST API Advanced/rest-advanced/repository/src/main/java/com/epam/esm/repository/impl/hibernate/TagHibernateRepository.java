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
        return findByName(object.getName()).isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
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
        Optional<Tag> result;
        try {
            TypedQuery<Tag> query = entityManager.createQuery(FIND_SINGLE_BY_NAME, Tag.class);
            query.setParameter("name", name);
            result = Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            log.error("[TagHibernateRepository.findByName()] NoResultException, Optional.empty() returned!!!");
            return Optional.empty();
        }
        return result;
    }

    @Override
    public List<Tag> findAllByName(String name) {
        TypedQuery<Tag> query = entityManager.createQuery(
                FIND_ALL_BY_NAME, Tag.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Tag> findAll() {
        return entityManager.createQuery(FIND_ALL, Tag.class)
                .getResultList();
    }

    @Override
    public List<Tag> findAllByCertificate(Long certificateId) {
        TypedQuery<Tag> query = entityManager.createQuery(
                FIND_ALL_BY_CERTIFICATE, Tag.class);
        query.setParameter("id",certificateId);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Long deleteById(Long id) {
        Tag tag;
        Optional<Tag> tagOptional = findById(id);
        if (tagOptional.isPresent()) {
            tag = tagOptional.get();
            tag.getGiftCertificates().forEach(giftCertificate -> giftCertificate.getTags().remove(tag));
            log.debug("Tag for removal {}", tag);
            entityManager.remove(tag);
            return tag.getId();
        }
        return 0L;
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
