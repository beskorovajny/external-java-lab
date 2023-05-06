package com.epam.esm.repository.impl.hibernate;

import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.core.model.Tag;
import com.epam.esm.core.model.User;
import com.epam.esm.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
@RequiredArgsConstructor
@Getter
public class UserJPARepository implements UserRepository {
    private static final String FIND_BY_NAME = "SELECT u FROM User u WHERE u.firstName= :name";
    private static final String FIND_ALL = "SELECT u FROM User u";

    private static final String FIND_ALL_BY_NAME = "SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(:name)";
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public boolean isExists(User object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User save(User object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> result;
        try {
            TypedQuery<User> query = entityManager.createQuery(FIND_BY_NAME, User.class);
            query.setParameter("name", name);
            result = Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            log.error("[UserJPARepository.findByName()] NoResultException, Optional.empty() has been returned!!!");
            return Optional.empty();
        }
        return result;
    }

    public List<User> findAllByName(String name) {
        TypedQuery<User> query = entityManager.createQuery(
                        FIND_ALL_BY_NAME, User.class)
                .setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery(FIND_ALL, User.class)
                .getResultList();
    }

    @Override
    public Long deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }
}
