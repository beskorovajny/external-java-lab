package com.epam.esm.service;

import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.model.pagination.Pageable;
import com.epam.esm.core.model.request.CreateReceiptRequestBody;

import java.util.List;

public interface ReceiptService {
    ReceiptDTO save(CreateReceiptRequestBody receiptRequestBody);
    ReceiptDTO findById(Long id);
    List<ReceiptDTO> findAll(Pageable pageable);
    List<ReceiptDTO> findAllByUser(Long userID, Pageable pageable);
    ReceiptDTO deleteById(Long id);
}
