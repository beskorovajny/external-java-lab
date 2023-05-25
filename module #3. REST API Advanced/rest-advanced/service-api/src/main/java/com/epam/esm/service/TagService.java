package com.epam.esm.service;

import com.epam.esm.core.dto.TagDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    TagDTO save(TagDTO tagDTO);

    TagDTO findById(Long id);

    Page<TagDTO> findAll(Pageable pageable);

    TagDTO findByName(String name);

    Page<TagDTO> findAllByCertificate(Long certificateID, Pageable pageable);

    TagDTO findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();

    TagDTO deleteById(Long id);
}
