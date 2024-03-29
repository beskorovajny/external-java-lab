package com.epam.esm.service;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.repository.utils.QueryParams;

import java.util.List;

public interface GiftCertificateService extends GenericService<GiftCertificateDTO, Long> {
    List<GiftCertificateDTO> findAllWithParams(QueryParams queryParams);

    void update(GiftCertificateDTO giftCertificateDTO);
}
