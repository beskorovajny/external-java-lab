package com.epam.esm.service.mapping;

import com.epam.esm.service.MappingService;
import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.core.model.Receipt;
import com.epam.esm.core.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptMappingService implements MappingService<Receipt, ReceiptDTO> {
    private final MappingService<GiftCertificate, GiftCertificateDTO> certificateMappingService;
    private final MappingService<User, UserDTO> userMappingService;

    @Override
    public Receipt mapFromDto(ReceiptDTO receiptDTO) {
        Receipt model = new Receipt();
        BeanUtils.copyProperties(receiptDTO, model);
        if (receiptDTO.getGiftCertificates() != null && !receiptDTO.getGiftCertificates().isEmpty()) {
            receiptDTO.getGiftCertificates().forEach(certificateDTO ->
                    model.getGiftCertificates().add(certificateMappingService.mapFromDto(certificateDTO)));
        }
        if (receiptDTO.getUserDTO() != null) {
            model.setUser(userMappingService.mapFromDto(receiptDTO.getUserDTO()));
        }
        log.debug("[ReceiptMappingService] ReceiptDTO: [{}] converted to Receipt model: [{}]", receiptDTO, model);
        return model;
    }

    @Override
    public ReceiptDTO mapToDto(Receipt model) {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        BeanUtils.copyProperties(model, receiptDTO, "giftCertificates", "user");
        log.debug("[ReceiptMappingService] Receipt model:[{}] converted to ReceiptDTO: [{}]", model, receiptDTO);
        return receiptDTO;
    }
}
