package com.epam.esm.service.mapping;

import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.model.Tag;
import com.epam.esm.service.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TagMappingServiceImpl implements MappingService<Tag, TagDTO> {
    @Override
    public Tag mapFromDto(TagDTO dto) {
        Tag model = new Tag();
        BeanUtils.copyProperties(dto, model);
        log.debug("[TagMappingService] TagDTO converted to Tag model: [{}]", model);
        return model;
    }

    @Override
    public TagDTO mapToDto(Tag model) {
        TagDTO dto = new TagDTO();
        BeanUtils.copyProperties(model, dto);
        log.debug("[TagMappingService] Tag model converted to TagDTO: [{}]", dto);
        return dto;
    }
}
