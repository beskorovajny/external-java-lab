package com.epam.esm.service;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.repository.utils.QueryParams;

import java.util.List;
import java.util.Set;

public interface GiftCertificateService extends GenericService<GiftCertificateDTO, Long> {
    List<GiftCertificateDTO> findAllByTags(Set<String> tags);

    List<GiftCertificateDTO> findAllByName(String name);

    List<GiftCertificateDTO> findAllWithParams(QueryParams queryParams);

    GiftCertificateDTO update(GiftCertificateDTO giftCertificateDTO);
}
