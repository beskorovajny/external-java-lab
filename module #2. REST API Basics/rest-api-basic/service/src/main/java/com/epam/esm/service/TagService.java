package com.epam.esm.service;

import com.epam.esm.dto.TagDTO;

import java.util.List;

public interface TagService {
    boolean isExists(TagDTO tagDTO);

    void save(TagDTO tagDTO);

    TagDTO findById(Long id);

    TagDTO findByName(String name);

    List<TagDTO> findAll();

    void deleteById(Long id);
}
