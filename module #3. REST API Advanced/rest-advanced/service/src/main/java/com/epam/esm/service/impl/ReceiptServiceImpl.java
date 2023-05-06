package com.epam.esm.service.impl;

import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.exception.ReceiptAlreadyExistsException;
import com.epam.esm.core.exception.ReceiptNotFoundException;
import com.epam.esm.core.exception.TagAlreadyExistsException;
import com.epam.esm.core.exception.TagNotFoundException;
import com.epam.esm.core.model.Receipt;
import com.epam.esm.core.model.Tag;
import com.epam.esm.repository.ReceiptRepository;
import com.epam.esm.service.ReceiptService;
import com.epam.esm.service.mapping.ReceiptMappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMappingService mappingService;
    @Override
    public void save(ReceiptDTO receiptDTO) {
        Receipt receipt = mappingService.mapFromDto(receiptDTO);
        log.debug("is exists receipt : {}", receiptRepository.isExists(receipt));
        if (receiptRepository.isExists(receipt)) {
            log.error("[ReceiptService.save()] Receipt with given title:[{}] already exists.", receipt.getTitle());
            throw new ReceiptAlreadyExistsException(String.format("Receipt with given title:[%s] already exists.",
                    receipt.getTitle()));
        }
        receiptRepository.save(receipt);
    }

    @Override
    public ReceiptDTO findById(Long id) {
        if (id == null || id < 1) {
            log.error("[ReceiptService.findById()] An exception occurs: id:[{}] can't be less than zero or null", id);
            throw new IllegalArgumentException("An exception occurs: Receipt.id can't be less than zero or null");
        }
        ReceiptDTO receiptDTO = receiptRepository.findById(id)
                .map(mappingService::mapToDto)
                .orElseThrow(() -> {
                    log.error("[Receipt.findById()] Receipt for given ID:[{}] not found", id);
                    throw new ReceiptNotFoundException(String.format("Receipt not found (id:[%d])", id));
                });

        log.debug("[ReceiptService.findById()] Receipt received from database: [{}], for ID:[{}]", receiptDTO, id);
        return receiptDTO;
    }


    @Override
    public List<ReceiptDTO> findAll() {
        List<ReceiptDTO> receiptDTOS = receiptRepository.findAll()
                .stream()
                .map(mappingService::mapToDto)
                .toList();
        if (receiptDTOS.isEmpty()) {
            log.error("[ReceiptService.findAll()] Receipts not found");
            throw new ReceiptNotFoundException("Receipts not found");
        }
        log.debug("[ReceiptService.findAll()] Receipts received from database: [{}]", receiptDTOS);
        return receiptDTOS;
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id < 1) {
            log.error("[ReceiptService.deleteById()] An exception occurs: id:[{}] can't be less than zero", id);
            throw new IllegalArgumentException("Receipt.id can't be less than zero.");
        }
        Optional<Receipt> receipt = receiptRepository.findById(id);
        log.debug("Delete receipt : {}" , receipt);
        if (receipt.isEmpty() || !receiptRepository.isExists(receipt.get())) {
            log.error("[ReceiptService.deleteById()] Receipt with given id:[{}] not found.", id);
            throw new ReceiptNotFoundException(String.format("Receipt with given id:[%d] not found for delete.", id));
        }
        receiptRepository.deleteById(id);
        log.debug("[ReceiptService.deleteById()] Receipt for ID:[{}] has been removed", id);
    }
}
