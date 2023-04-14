package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.repository.util.QueryParams;

import java.util.List;

public interface GiftCertificateService extends GenericService<GiftCertificateDTO, Long> {
    List<GiftCertificateDTO> findAllWithParams(QueryParams queryParams);

    void update(GiftCertificateDTO giftCertificateDTO);
}
