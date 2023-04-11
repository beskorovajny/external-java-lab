package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.repository.util.QueryParams;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {
    void save(GiftCertificateDTO giftCertificate);

    GiftCertificateDTO findById(Long id);

    List<GiftCertificateDTO> findAll();
    List<GiftCertificateDTO> findAllByName(String name);
    List<GiftCertificateDTO> findAllWithParams(QueryParams queryParams);

    void update(Long id, GiftCertificateDTO giftCertificateDTO);

    void deleteById(Long id);
}
