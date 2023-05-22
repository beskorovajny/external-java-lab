package com.epam.esm.service;

import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.model.pagination.Pageable;

import java.util.List;

public interface TagService {
    TagDTO save(TagDTO tagDTO);
    TagDTO findById(Long id);
    List<TagDTO> findAll(Pageable pageable);
    TagDTO findByName(String name);
    List<TagDTO> findAllByCertificate(Long certificateID, Pageable pageable);
    TagDTO findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();
    TagDTO deleteById(Long id);
}
