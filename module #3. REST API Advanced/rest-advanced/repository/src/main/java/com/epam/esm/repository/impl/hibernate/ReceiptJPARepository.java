package com.epam.esm.repository.impl.hibernate;

import com.epam.esm.core.exception.ReceiptNotFoundException;
import com.epam.esm.core.model.Receipt;
import com.epam.esm.repository.ReceiptRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
public class ReceiptJPARepository implements ReceiptRepository {
    private static final String FIND_ALL_BY_TITLE = "SELECT r FROM Receipt r WHERE LOWER(r.title) LIKE LOWER(:title)";
    private static final String FIND_BY_TITLE = "SELECT r FROM Receipt r WHERE r.title= :title";
    private static final String FIND_ALL = "SELECT r FROM Receipt r";
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean isExists(Receipt object) {
        return findById(object.getId()).isPresent();
    }

    @Transactional
    @Override
    public Receipt save(Receipt receipt) {
        entityManager.persist(receipt);
        log.debug("[ReceiptJPARepository.save()] Receipt with id:[{}] has been saved.", receipt.getId());
        return receipt;
    }

    @Override
    public Optional<Receipt> findById(Long id) {
        Receipt receipt = entityManager.find(Receipt.class, id);
        return Optional.ofNullable(receipt);
    }

    @Override
    public List<Receipt> findAll() {
        return entityManager.createQuery(FIND_ALL, Receipt.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Long deleteById(Long id) {
        Receipt receipt;
        Optional<Receipt> receiptOptional = findById(id);
        if (receiptOptional.isEmpty()) {
            throw new ReceiptNotFoundException("Receipt not found");
        }
        receipt = receiptOptional.get();
        receipt.getGiftCertificates().forEach(giftCertificate -> giftCertificate.getReceipts().remove(receipt));
        log.debug("Receipt for removal {}", receipt);
        entityManager.remove(receipt);
        return receipt.getId();
    }

}
