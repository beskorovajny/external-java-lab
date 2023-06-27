package com.epam.esm.service;

import com.epam.esm.core.dto.ReceiptDTO;

import com.epam.esm.core.payload.request.ReceiptRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceiptService {

    ReceiptDTO save(ReceiptRequestBody receiptRequestBody, String auth);

    ReceiptDTO findById(Long id);

    Page<ReceiptDTO> findAll(Pageable pageable);

    Page<ReceiptDTO> findAllByUser(Long userID, Pageable pageable);

    ReceiptDTO deleteById(Long id);
}
