package com.epam.esm.service.mapping;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.core.model.Tag;
import com.epam.esm.service.MappingService;
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
        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            dto.getTags().forEach(tagDTO -> {
                com.epam.esm.core.model.Tag tag = new Tag();
                BeanUtils.copyProperties(tagDTO, tag);
                model.getTags().add(tag);
            });
        }
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
