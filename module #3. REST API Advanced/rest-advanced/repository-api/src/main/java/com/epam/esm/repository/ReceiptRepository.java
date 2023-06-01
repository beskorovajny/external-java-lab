package com.epam.esm.repository;

import com.epam.esm.core.model.entity.Receipt;
import com.epam.esm.core.model.pagination.Pageable;

import java.util.List;

public interface ReceiptRepository extends GenericRepository<Receipt, Long> {
    List<Receipt> findAllByUser(Long userID, Pageable pageable);
    Long getTotalRecordsForUserID(Long userID);
}
