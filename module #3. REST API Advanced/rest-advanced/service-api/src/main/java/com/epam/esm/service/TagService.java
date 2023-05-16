package com.epam.esm.service;

import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.model.Tag;

import java.util.List;

public interface TagService extends GenericService<TagDTO, Long> {
    TagDTO findByName(String name);
    List<TagDTO> findAllByCertificate(Long certificateId);
}
