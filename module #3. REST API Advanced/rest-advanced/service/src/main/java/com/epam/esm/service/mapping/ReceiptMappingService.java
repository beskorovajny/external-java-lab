package com.epam.esm.service.mapping;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.core.model.Receipt;
import com.epam.esm.core.model.Tag;
import com.epam.esm.core.model.User;
import com.epam.esm.service.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiptMappingService implements MappingService<Receipt, ReceiptDTO> {
    @Override
    public Receipt mapFromDto(ReceiptDTO dto) {
        Receipt model = new Receipt();
        BeanUtils.copyProperties(dto, model);
        if (dto.getGiftCertificates() != null && !dto.getGiftCertificates().isEmpty()) {
            dto.getGiftCertificates().forEach(certificateDTO -> {
                GiftCertificate giftCertificate = new GiftCertificate();
                BeanUtils.copyProperties(certificateDTO, giftCertificate);
                model.getGiftCertificates().add(giftCertificate);
            });
        }
        if (dto.getUserDTO() != null) {
            User user = new User();
            BeanUtils.copyProperties(dto.getUserDTO(), user);
            model.setUser(user);
        }
        log.debug("[ReceiptMappingService] ReceiptDTO converted to Receipt model: [{}]", model);
        return model;
    }

    @Override
    public ReceiptDTO mapToDto(Receipt model) {
        ReceiptDTO dto = new ReceiptDTO();
        BeanUtils.copyProperties(model, dto);
        if (model.getGiftCertificates() != null && !model.getGiftCertificates().isEmpty()) {
            model.getGiftCertificates().forEach(giftCertificate -> {
                GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
                BeanUtils.copyProperties(giftCertificate, giftCertificateDTO);
                dto.getGiftCertificates().add(giftCertificateDTO);
            });
        }
        if (model.getUser() != null) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(model.getUser(), userDTO);
            dto.setUserDTO(userDTO);
        }
        log.debug("[ReceiptMappingService] Receipt model converted to ReceiptDTO: [{}]", dto);
        return dto;
    }
}
