package com.epam.esm.service.impl;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.exception.model.GiftCertificateAlreadyExistsException;
import com.epam.esm.exception.model.GiftCertificateNotFoundException;
import com.epam.esm.exception.model.TagNotFoundException;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateRepository giftCertificateRepository;
    private final MappingService<GiftCertificate, GiftCertificateDTO> mappingService;

    @Override
    public boolean isExists(GiftCertificateDTO giftCertificateDTO) {
        Validate.notNull(giftCertificateDTO, "GiftCertificateDTO can't be Null");
        return giftCertificateRepository.findByName(giftCertificateDTO.getName()).isPresent();
    }

    @Override
    public void save(GiftCertificateDTO giftCertificateDTO) {
        if (isExists(giftCertificateDTO)) {
            log.error("[GiftCertificateService.save()] GiftCertificate with given name:[{}] already exists.",
                    giftCertificateDTO.getName());
            throw new GiftCertificateAlreadyExistsException(String.format(
                    "GiftCertificate with given name:[%s] already exists.", giftCertificateDTO.getName()));
        }
        giftCertificateDTO.setCreateDate(LocalDateTime.now());
        GiftCertificate giftCertificate = mappingService.mapFromDto(giftCertificateDTO);
        Long id = giftCertificateRepository.save(giftCertificate);

        if (id > 0) log.debug("[GiftCertificateService.save()] GiftCertificate saved with id:[{}]", id);
        else log.error("[GiftCertificateService.save()] GiftCertificate was not saved, GiftCertificate.name: [{}]",
                giftCertificate.getName());
    }

    @Override
    public GiftCertificateDTO findById(Long id) {
        if (id == null || id < 1) {
            log.error("[GiftCertificateService.findById()] An exception occurs: id:[{}]" +
                    " can't be less than zero or null", id);
            throw new IllegalArgumentException("GiftCertificate.id can't be less than zero or null");
        }

        GiftCertificateDTO giftCertificateDTO = giftCertificateRepository.findById(id)
                .map(mappingService::mapToDto)
                .orElseThrow(() -> {
                    log.error("[GiftCertificateService.findById()] GiftCertificate for given ID:[{}] not found", id);
                    throw new GiftCertificateNotFoundException(String.format("GiftCertificate not found (id:[%d])", id));
                });

        log.debug("[GiftCertificateService.findById()] GiftCertificate received from database: [{}], for ID:[{}]",
                giftCertificateDTO, id);

        return giftCertificateDTO;
    }

    @Override
    public GiftCertificateDTO findByName(String name) {
        Validate.notBlank(name);
        GiftCertificateDTO giftCertificateDTO = giftCertificateRepository.findByName(name)
                .map(mappingService::mapToDto)
                .orElseThrow(() -> {
                    log.error("[GiftCertificateService.findByName()] GiftCertificate for given name:[{}] not found",
                            name);
                    throw new GiftCertificateNotFoundException(String.format("GiftCertificate not found (name:[%s])", name));
                });

        log.debug("[GiftCertificateService.findByName()] GiftCertificate received from database: [{}], for name:[{}]"
                , giftCertificateDTO, name);

        return giftCertificateDTO;
    }

    @Override
    public List<GiftCertificateDTO> findAll() {
        List<GiftCertificateDTO> certificates = giftCertificateRepository
                .findAll()
                .stream()
                .flatMap(Collection::stream)
                .map(mappingService::mapToDto)
                .collect(Collectors.toList());

        if (certificates.isEmpty()) log.error("[GiftCertificateService.findAll()] GiftCertificates not found");
        else log.debug("[GiftCertificateService.findAll()] GiftCertificates received from database:[{}]", certificates);

        return certificates;
    }

    @Override
    public void update(Long id, GiftCertificateDTO giftCertificateDTO) {
        Validate.notNull(giftCertificateDTO, "GiftCertificateDTO can't be Null");
        if (id == null || id < 1) {
            log.error("[GiftCertificateService.update()] An exception occurs: given ID:[{}]" +
                    " can't be less than zero or null", id);
            throw new IllegalArgumentException("Given ID can't be less than zero or null");
        }

        GiftCertificate giftCertificate = mappingService.mapFromDto(giftCertificateDTO);
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        giftCertificateRepository.update(id, giftCertificate);

        log.debug("[GiftCertificateService.update()] GiftCertificate with ID:[{}] updated.", id);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id < 1) {
            log.error("[GiftCertificateService.deleteById()] " +
                    "An exception occurs: id:[{}] can't be less than zero or null", id);
            throw new IllegalArgumentException("GiftCertificate.id can't be less than zero or null");
        }
        if (giftCertificateRepository.findById(id).isEmpty()) {
            log.error("[GiftCertificateService.deleteById()] Certificate with given id:[{}] not found.", id);
            throw new GiftCertificateNotFoundException(String
                    .format("Certificate with given id:[%d] not found for delete.", id));
        }
        giftCertificateRepository.deleteById(id);
        log.debug("[GiftCertificateService.deleteById()] GiftCertificate for ID:[{}] removed.", id);
    }
}
