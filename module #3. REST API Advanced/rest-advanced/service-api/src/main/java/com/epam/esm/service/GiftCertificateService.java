package com.epam.esm.service;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.model.Pageable;
import com.epam.esm.core.model.QueryParams;

import java.util.List;
import java.util.Set;

public interface GiftCertificateService extends GenericService<GiftCertificateDTO, Long> {
    List<GiftCertificateDTO> findAllByTags(Set<String> tags, Pageable pageable);

    List<GiftCertificateDTO> findAllByName(String name, Pageable pageable);

    List<GiftCertificateDTO> findAllWithParams(QueryParams queryParams, Pageable pageable);

    GiftCertificateDTO update(GiftCertificateDTO giftCertificateDTO);
}
