package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.model.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {
    void save(GiftCertificateDTO giftCertificate);

    GiftCertificateDTO findById(Long id);

    List<GiftCertificateDTO> findAll();

    List<GiftCertificateDTO> findAllByName(String name);

    List<GiftCertificateDTO> findAllByDescription(String description);

    List<GiftCertificateDTO> findAllByTag(String tagName);

    List<GiftCertificateDTO> findAllByTagAndName(String tagName, String name);
    List<GiftCertificateDTO> findAllByTagAndDescription(String tagName, String description);

    void update(Long id, GiftCertificateDTO giftCertificateDTO);

    void deleteById(Long id);
}
