package com.epam.esm.service.mapping.impl;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.service.mapping.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GiftCertificateMappingServiceImpl implements MappingService<GiftCertificate, GiftCertificateDTO> {
    @Override
    public GiftCertificate mapFromDto(GiftCertificateDTO dto) {
        GiftCertificate model = new GiftCertificate();
        BeanUtils.copyProperties(dto, model);
        log.debug("[GiftCertificateMappingService] GiftCertificateDTO converted to GiftCertificate model: [{}]", model);
        return model;
    }

    @Override
    public GiftCertificateDTO mapToDto(GiftCertificate model) {
        GiftCertificateDTO dto = new GiftCertificateDTO();
        BeanUtils.copyProperties(model, dto);
        log.debug("[GiftCertificateMappingService] GiftCertificate model converted to GiftCertificateDTO: [{}]", dto);
        return dto;
    }
}
