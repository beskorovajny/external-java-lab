package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;

import java.util.List;

public interface GiftCertificateService {
    boolean isExists(GiftCertificateDTO giftCertificate);

    void save(GiftCertificateDTO giftCertificate);

    GiftCertificateDTO findById(Long id);

    GiftCertificateDTO findByName(String name);

    List<GiftCertificateDTO> findAll();

    void update(Long id, GiftCertificateDTO giftCertificateDTO);

    void deleteById(Long id);
}
