package com.epam.esm.service;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.model.query.QueryParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
public interface GiftCertificateService {
    GiftCertificateDTO save(GiftCertificateDTO giftCertificateDTO);

    GiftCertificateDTO findById(Long id);

    Page<GiftCertificateDTO> findAll(Pageable pageable);

    Page<GiftCertificateDTO> findAllByTags(Set<String> tags, Pageable pageable);

    Page<GiftCertificateDTO> findAllByName(String name, Pageable pageable);

    Page<GiftCertificateDTO> findAllWithParams(QueryParams queryParams, Pageable pageable);

    Page<GiftCertificateDTO> findAllByReceipt(Long receiptID, Pageable pageable);

    GiftCertificateDTO update(GiftCertificateDTO giftCertificateDTO);

    GiftCertificateDTO updatePrice(Long giftCertificateID, Double price);

    GiftCertificateDTO deleteById(Long id);
}
