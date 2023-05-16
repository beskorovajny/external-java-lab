package com.epam.esm.service;

import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.model.Receipt;

import java.util.List;

public interface ReceiptService extends GenericService<ReceiptDTO, Long> {
    public List<ReceiptDTO> findAllByUser(Long userID);
}
