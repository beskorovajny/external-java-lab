package com.epam.esm.service;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.model.pagination.Pageable;
import com.epam.esm.core.model.query.QueryParams;

import java.util.List;
import java.util.Set;

public interface GiftCertificateService {
    GiftCertificateDTO save(GiftCertificateDTO giftCertificateDTO);

    GiftCertificateDTO findById(Long id);

    List<GiftCertificateDTO> findAll(Pageable pageable);

    List<GiftCertificateDTO> findAllByTags(Set<String> tags, Pageable pageable);

    List<GiftCertificateDTO> findAllByName(String name, Pageable pageable);

    List<GiftCertificateDTO> findAllWithParams(QueryParams queryParams, Pageable pageable);

    List<GiftCertificateDTO> findAllByReceipt(Long receiptID, Pageable pageable);

    GiftCertificateDTO update(GiftCertificateDTO giftCertificateDTO);
    GiftCertificateDTO updatePrice(Long giftCertificateID, Double price);

    GiftCertificateDTO deleteById(Long id);
}
