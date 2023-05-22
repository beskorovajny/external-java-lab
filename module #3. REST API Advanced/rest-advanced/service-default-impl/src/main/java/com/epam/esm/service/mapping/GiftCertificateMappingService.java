package com.epam.esm.service.mapping;

import com.epam.esm.service.MappingService;
import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.model.entity.GiftCertificate;
import com.epam.esm.core.model.entity.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GiftCertificateMappingService implements MappingService<GiftCertificate, GiftCertificateDTO> {
    private final MappingService<Tag, TagDTO> tagMappingService;

    @Override
    public GiftCertificate mapFromDto(GiftCertificateDTO certificateDTO) {
        GiftCertificate model = new GiftCertificate();
        BeanUtils.copyProperties(certificateDTO, model);
        if (certificateDTO.getTags() != null && !certificateDTO.getTags().isEmpty()) {
            certificateDTO.getTags().forEach(tagDTO -> model.getTags().add(tagMappingService.mapFromDto(tagDTO)));
        }
        log.debug("[GiftCertificateMappingService] GiftCertificateDTO: [{}] converted to GiftCertificate model: [{}]",
                certificateDTO ,model);
        return model;
    }

    @Override
    public GiftCertificateDTO mapToDto(GiftCertificate model) {
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
        BeanUtils.copyProperties(model, giftCertificateDTO, "tags");

        log.debug("[GiftCertificateMappingService.mapToDTO()] GiftCertificate model: [{}] converted to DTO: [{}]",
                model, giftCertificateDTO);
        return giftCertificateDTO;
    }
}
