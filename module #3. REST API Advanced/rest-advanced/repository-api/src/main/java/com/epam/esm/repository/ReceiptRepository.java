package com.epam.esm.repository;

import com.epam.esm.core.model.Receipt;

import java.util.List;

public interface ReceiptRepository extends GenericRepository<Receipt, Long> {
    List<Receipt> findAllByUser(Long userID);
}
