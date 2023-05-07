package com.epam.esm.service.mapping;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.core.model.Tag;
import com.epam.esm.service.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GiftCertificateMappingServiceImpl implements MappingService<GiftCertificate, GiftCertificateDTO> {
    @Override
    public GiftCertificate mapFromDto(GiftCertificateDTO dto) {
        GiftCertificate model = new GiftCertificate();
        BeanUtils.copyProperties(dto, model);
        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            dto.getTags().forEach(tagDTO -> {
                Tag tag = new Tag();
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
        if (model.getTags() != null && !model.getTags().isEmpty()) {
            model.getTags().forEach(tag -> {
                TagDTO tagDTO = new TagDTO();
                BeanUtils.copyProperties(tag, tagDTO);
                dto.getTags().add(tagDTO);
            });
        }
        log.debug("[GiftCertificateMappingService.mapToDTO()] Model converted to DTO: [{}]", dto);
        return dto;
    }
}
