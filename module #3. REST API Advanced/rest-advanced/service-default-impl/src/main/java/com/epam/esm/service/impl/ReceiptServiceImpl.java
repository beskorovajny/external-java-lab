package com.epam.esm.service.impl;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.exception.GiftCertificateNotFoundException;
import com.epam.esm.core.exception.ReceiptNotFoundException;
import com.epam.esm.core.model.entity.Receipt;
import com.epam.esm.core.model.pagination.Pageable;
import com.epam.esm.core.model.request.CreateReceiptRequestBody;
import com.epam.esm.repository.ReceiptRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.MappingService;
import com.epam.esm.service.ReceiptService;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.epam.esm.jpa.utils.PageableValidator.checkParams;
import static com.epam.esm.jpa.utils.PageableValidator.validate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final MappingService<Receipt, ReceiptDTO> mappingService;
    private final UserService userService;
    private final GiftCertificateService giftCertificateService;

    @Override
    public ReceiptDTO save(CreateReceiptRequestBody receiptRequestBody) {
        Validate.notNull(receiptRequestBody, "[ReceiptService.save()] ReceiptRequestBody can't be null!");
        ReceiptDTO receiptDTO = new ReceiptDTO();
        UserDTO userDTO = userService.findById(receiptRequestBody.getUserID());
        receiptDTO.setUserDTO(userDTO);
        setGiftCertificatesAndPrice(receiptDTO, receiptRequestBody);
        receiptDTO.setCreateDate(LocalDateTime.now());

        Receipt fromDto = mappingService.mapFromDto(receiptDTO);

        Receipt savedReceipt = receiptRepository.save(fromDto);
        log.debug("[ReceiptService.save()] Receipt saved: [{}]", savedReceipt);
        return mappingService.mapToDto(savedReceipt);
    }

    private void setGiftCertificatesAndPrice(ReceiptDTO receiptDTO, CreateReceiptRequestBody receiptRequestBody) {
        if (!receiptRequestBody.getGiftCertificatesIDs().isEmpty()) {
            Set<GiftCertificateDTO> giftCertificateDTOS = new HashSet<>();

            receiptRequestBody.getGiftCertificatesIDs().forEach(id -> {
                if (id != null) {
                    GiftCertificateDTO giftCertificateDTO = null;
                    try {
                        giftCertificateDTO = giftCertificateService.findById(id);
                    } catch (GiftCertificateNotFoundException e) {
                        log.error("[ReceiptService.save()] An exception occurs : [{}]", e.getMessage());
                    }
                    if (giftCertificateDTO != null) {
                        giftCertificateDTOS.add(giftCertificateDTO);
                    }
                }
            });
            if (giftCertificateDTOS.isEmpty()) {
                log.error("[ReceiptService.save()] GiftCertificates for new receipt not found");
                throw new GiftCertificateNotFoundException("GiftCertificates for new receipt not found");
            }
            Double price = giftCertificateDTOS.stream()
                    .mapToDouble(GiftCertificateDTO::getPrice)
                    .sum();
            receiptDTO.setPrice(price);
            receiptDTO.setGiftCertificates(giftCertificateDTOS);
        }
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
    public List<ReceiptDTO> findAll(Pageable pageable) {
        validate(pageable);
        Long totalRecords = receiptRepository.getTotalRecords();
        List<ReceiptDTO> receiptDTOS = receiptRepository
                .findAll(checkParams(pageable, totalRecords))
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
    public List<ReceiptDTO> findAllByUser(Long userID, Pageable pageable) {
        if (userID == null || userID < 1) {
            log.error("[ReceiptService.findAllByUser()] An exception occurs: User.ID:[{}]" +
                    " can't be less than zero or null", userID);
            throw new IllegalArgumentException("An exception occurs: User.ID can't be less than zero or null");
        }
        Long totalRecords = receiptRepository.getTotalRecordsForUserID(userID);
        List<ReceiptDTO> receipts = receiptRepository
                .findAllByUser(userID, checkParams(pageable, totalRecords))
                .stream()
                .map(mappingService::mapToDto)
                .toList();
        if (receipts.isEmpty()) {
            log.error("[ReceiptService.findAllByUser()] Receipts not found");
            throw new ReceiptNotFoundException("Receipts not found");
        }
        log.debug("[ReceiptService.findAllByUser()] Receipts received from database: [{}], for User.ID: [{}]",
                receipts, userID);
        return receipts;
    }

    @Override
    public ReceiptDTO deleteById(Long id) {
        if (id == null || id < 1) {
            log.error("[ReceiptService.deleteById()] An exception occurs: id:[{}] can't be less than zero", id);
            throw new IllegalArgumentException("Receipt.id can't be less than zero.");
        }
        Optional<Receipt> receipt = receiptRepository.findById(id);
        log.debug("Delete receipt : {}", receipt);
        if (receipt.isEmpty() || !receiptRepository.isExists(receipt.get())) {
            log.error("[ReceiptService.deleteById()] Receipt with given id:[{}] not found.", id);
            throw new ReceiptNotFoundException(String.format("Receipt with given id:[%d] not found for delete.", id));
        }

        Receipt removedReceipt = receiptRepository.deleteById(id);
        log.debug("[ReceiptService.deleteById()] Receipt for ID:[{}] has been removed", id);
        return mappingService.mapToDto(removedReceipt);
    }
}
