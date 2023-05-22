package com.epam.esm.jpa.impl.hibernate;

import com.epam.esm.core.model.entity.User;
import com.epam.esm.core.model.pagination.Pageable;
import com.epam.esm.jpa.utils.PageableValidator;
import com.epam.esm.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserJPARepository implements UserRepository {
    private static final String FIND_BY_NAME = "SELECT u FROM User u WHERE u.firstName= :name";
    private static final String FIND_ALL = "SELECT u FROM User u";
    private static final String FIND_ALL_BY_NAME = "SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(:name)";
    private static final String FIND_BY_RECEIPT = "SELECT r.user FROM Receipt r WHERE r.id = :id";
    private static final String GET_TOTAL_RECORDS = "SELECT COUNT(u.id) from User u";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isExists(User user) {
        return findById(user.getId()).isPresent();
    }

    @Override
    public User save(User user) {
        return entityManager.merge(user);
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
            result = Optional.ofNullable(entityManager
                    .createQuery(FIND_BY_NAME, User.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            log.error("[UserJPARepository.findByName()] NoResultException, Optional.empty() has been returned!!!");
            return Optional.empty();
        }
        return result;
    }

    @Override
    public Optional<User> findByReceipt(Long receiptID) {
        Optional<User> result;
        try {
            result = Optional.ofNullable(entityManager
                    .createQuery(FIND_BY_RECEIPT, User.class)
                    .setParameter("id", receiptID)
                    .getSingleResult());
        } catch (NoResultException e) {
            log.error("[UserJPARepository.findByReceipt()] NoResultException, Optional.empty() has been returned!!!");
            return Optional.empty();
        }
        return result;
    }

    @Override
    public List<User> findAllByName(String name, Pageable pageable) {
        int firstResult = PageableValidator.getFirstResultValue(pageable);
        return entityManager
                .createQuery(FIND_ALL_BY_NAME, User.class)
                .setParameter("name", "%" + name + "%")
                .setFirstResult(firstResult)
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        int firstResult = PageableValidator.getFirstResultValue(pageable);
        return entityManager
                .createQuery(FIND_ALL, User.class)
                .setFirstResult(firstResult)
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    @Override
    public User deleteById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        return user;
    }

    @Override
    public Long getTotalRecords() {
        return entityManager.createQuery(GET_TOTAL_RECORDS, Long.class).getSingleResult();
    }
}
