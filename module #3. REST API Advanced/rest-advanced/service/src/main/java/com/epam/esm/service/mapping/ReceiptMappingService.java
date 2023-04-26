package com.epam.esm.service.mapping;

import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.model.Receipt;
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
        log.debug("[ReceiptMappingService] ReceiptDTO converted to Receipt model: [{}]", model);
        return model;
    }

    @Override
    public ReceiptDTO mapToDto(Receipt model) {
        ReceiptDTO dto = new ReceiptDTO();
        BeanUtils.copyProperties(model, dto);
        log.debug("[ReceiptMappingService] Receipt model converted to ReceiptDTO: [{}]", dto);
        return dto;
    }
}
