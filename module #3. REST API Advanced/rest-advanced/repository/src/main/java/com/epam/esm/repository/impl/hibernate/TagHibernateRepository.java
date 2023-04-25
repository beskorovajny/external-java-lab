package com.epam.esm.repository.impl.hibernate;

import com.epam.esm.core.model.Tag;
import com.epam.esm.repository.TagRepository;
import jakarta.persistence.EntityManager;
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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isExists(Tag object) {
        return findByName(object.getName()).isPresent();
    }

    @Transactional
    @Override
    public Long save(Tag tag) {
        entityManager.persist(tag);
        Long id = tag.getId();
        log.debug("[TagHibernateRepository.save()] Tag with id:[{}] has been saved.", id);
        return id;
    }

    @Override
    public Optional<Tag> findById(Long id) {
        Tag tag = entityManager.find(Tag.class, id);
        entityManager.detach(tag);
        return Optional.of(tag);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        TypedQuery<Tag> query = entityManager.createQuery(
                "SELECT t FROM Tag t WHERE t.name= :name", Tag.class);
        query.setParameter("name", name);
        log.debug("save result :{}", query.getSingleResult());
        return Optional.of(query.getSingleResult());
    }

    @Override
    public Optional<List<Tag>> findAllByName(String name) {
        TypedQuery<Tag> query = entityManager.createQuery(
                "SELECT t FROM Tag t WHERE LOWER(t.name) LIKE LOWER(:name)", Tag.class);
        query.setParameter("name", "%" + name + "%");
        return Optional.of(query.getResultList());
    }

    @Override
    public Optional<List<Tag>> findAll() {
        return Optional.of(entityManager.createQuery("SELECT t FROM Tag t", Tag.class)
                .getResultList());
    }

    @Override
    public Optional<List<Tag>> findAllByCertificate(Long certificateId) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Tag tag = entityManager.find(Tag.class, id);
        entityManager.remove(tag);
    }

}
