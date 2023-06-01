package com.epam.esm.repository;

import com.epam.esm.core.model.entity.Receipt;
<<<<<<< HEAD
import org.springframework.data.domain.Pageable;
=======
import com.epam.esm.core.model.pagination.Pageable;
>>>>>>> module_3

import java.util.List;

public interface ReceiptRepository extends GenericRepository<Receipt, Long> {
    List<Receipt> findAllByUser(Long userID, Pageable pageable);
<<<<<<< HEAD

=======
>>>>>>> module_3
    Long getTotalRecordsForUserID(Long userID);
}
