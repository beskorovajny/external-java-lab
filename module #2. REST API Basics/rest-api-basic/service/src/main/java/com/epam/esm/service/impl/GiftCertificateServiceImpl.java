package com.epam.esm.service.impl;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.exception.model.GiftCertificateAlreadyExistsException;
import com.epam.esm.exception.model.GiftCertificateNotFoundException;
import com.epam.esm.exception.model.TagNotFoundException;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private static final String TAG_FOR_NAME_NOT_FOUND = "Tag for name:[%s] not found";
    private final GiftCertificateRepository giftCertificateRepository;
    private final TagRepository tagRepository;
    private final MappingService<GiftCertificate, GiftCertificateDTO> certificateMappingService;
    private final MappingService<Tag, TagDTO> tagMappingService;


    @Override
    public void save(GiftCertificateDTO giftCertificateDTO) {
        if (giftCertificateRepository.isExists(giftCertificateDTO)) {
            log.error("[GiftCertificateService.save()] GiftCertificate with given name:[{}] already exists.",
                    giftCertificateDTO.getName());
            throw new GiftCertificateAlreadyExistsException(String.format(
                    "GiftCertificate with given name:[%s] already exists.", giftCertificateDTO.getName()));
        }
        giftCertificateDTO.setCreateDate(LocalDateTime.now());
        GiftCertificate giftCertificate = certificateMappingService.mapFromDto(giftCertificateDTO);
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
                .map(certificateMappingService::mapToDto)
                .orElseThrow(() -> {
                    log.error("[GiftCertificateService.findById()] GiftCertificate for given ID:[{}] not found", id);
                    throw new GiftCertificateNotFoundException(String.format("GiftCertificate not found (id:[%d])", id));
                });
        List<TagDTO> tags = getMappedAndCollected(giftCertificateDTO);
        giftCertificateDTO.setTags(new HashSet<>(tags));

        log.debug("[GiftCertificateService.findById()] GiftCertificate received from database: [{}], for ID:[{}]",
                giftCertificateDTO, id);

        return giftCertificateDTO;
    }

    @Override
    public List<GiftCertificateDTO> findAllByName(String name) {
        Validate.notBlank(name);
        List<GiftCertificateDTO> certificates = giftCertificateRepository.findByName(name)
                .stream()
                .flatMap(Collection::stream)
                .map(certificateMappingService::mapToDto)
                .collect(Collectors.toList());

        if (certificates.isEmpty()) {
            log.error("[GiftCertificateService.findByName()] GiftCertificate for given name:[{}] not found",
                    name);
            throw new GiftCertificateNotFoundException(String.format("GiftCertificate not found (name:[%s])", name));
        } else {
            for (GiftCertificateDTO certificateDTO : certificates) {
                List<TagDTO> tags = getMappedAndCollected(certificateDTO);
                certificateDTO.setTags(new HashSet<>(tags));
            }
            log.debug("[GiftCertificateService.findByName()] GiftCertificate received from database: [{}], for name:[{}]"
                    , certificates, name);
            return certificates;
        }
    }

    @Override
    public List<GiftCertificateDTO> findAllByDescription(String description) {
        Validate.notBlank(description);
        List<GiftCertificateDTO> certificates = giftCertificateRepository.findAllByDescription(description)
                .stream()
                .flatMap(Collection::stream)
                .map(certificateMappingService::mapToDto)
                .collect(Collectors.toList());

        if (certificates.isEmpty()) {
            log.error("[GiftCertificateService.findAllByDesc()] GiftCertificate for given description:[{}] not found",
                    description);
            throw new GiftCertificateNotFoundException(String.format("GiftCertificate not found (description:[%s])",
                    description));
        } else {
            for (GiftCertificateDTO certificateDTO : certificates) {
                List<TagDTO> tags = getMappedAndCollected(certificateDTO);
                certificateDTO.setTags(new HashSet<>(tags));
            }
            log.debug("[GiftCertificateService.findAllByDesc()] GiftCertificate received from database: [{}]," +
                    " for description:[{}]", certificates, description);
            return certificates;
        }
    }

    @Override
    public List<GiftCertificateDTO> findAll() {
        List<GiftCertificateDTO> certificates = giftCertificateRepository.findAll()
                .stream()
                .flatMap(Collection::stream)
                .map(certificateMappingService::mapToDto)
                .collect(Collectors.toList());
        return getCertificateDTOSWithTags(certificates);
    }

    @Override
    public List<GiftCertificateDTO> findAllByTag(String tagName) {
        List<GiftCertificateDTO> certificates;
        if (tagRepository.findByName(tagName).isPresent()) {
            Optional<List<Tag>> tags = tagRepository.findByName(tagName);
            if (tags.isEmpty() || tags.get().isEmpty()) {
                throw new TagNotFoundException(String.format(TAG_FOR_NAME_NOT_FOUND, tagName));
            } else {
                Tag tag = tags.get().get(0);
                certificates = giftCertificateRepository.findAllByTagId(tag.getId())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(certificateMappingService::mapToDto)
                        .collect(Collectors.toList());
                return getCertificateDTOSWithTags(certificates);
            }
        } else {
            throw new TagNotFoundException(String.format(TAG_FOR_NAME_NOT_FOUND, tagName));
        }
    }

    @Override
    public List<GiftCertificateDTO> findAllByTagAndName(String tagName, String name) {
        List<GiftCertificateDTO> certificates = new ArrayList<>();
        if (tagRepository.findByName(tagName).isPresent()) {
            Optional<List<Tag>> tags = tagRepository.findByName(tagName);
            if (tags.isPresent()) {
                Tag tag = tags.get().get(0);
                certificates = giftCertificateRepository.findAllByTagIdAndName(tag.getId(), name)
                        .stream()
                        .flatMap(Collection::stream)
                        .map(certificateMappingService::mapToDto)
                        .collect(Collectors.toList());
                return getCertificateDTOSWithTags(certificates);
            }
        } else {
            throw new TagNotFoundException(String.format(TAG_FOR_NAME_NOT_FOUND, tagName));
        }
        return certificates;
    }

    @Override
    public List<GiftCertificateDTO> findAllByTagAndDescription(String tagName, String description) {
        List<GiftCertificateDTO> certificates = new ArrayList<>();
        if (tagRepository.findByName(tagName).isPresent()) {
            Optional<List<Tag>> tags = tagRepository.findByName(tagName);
            if (tags.isPresent()) {
                Tag tag = tags.get().get(0);
                certificates = giftCertificateRepository.findAllByTagIdAndDescription(tag.getId(), description)
                        .stream()
                        .flatMap(Collection::stream)
                        .map(certificateMappingService::mapToDto)
                        .collect(Collectors.toList());
                return getCertificateDTOSWithTags(certificates);
            }
        } else {
            throw new TagNotFoundException(String.format(TAG_FOR_NAME_NOT_FOUND, tagName));
        }
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

        GiftCertificate giftCertificate = certificateMappingService.mapFromDto(giftCertificateDTO);
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

    private List<TagDTO> getMappedAndCollected(GiftCertificateDTO giftCertificateDTO) {
        return tagRepository.findAllByCertificate(giftCertificateDTO.getId())
                .stream()
                .flatMap(Collection::stream)
                .map(tagMappingService::mapToDto)
                .collect(Collectors.toList());
    }

    private List<GiftCertificateDTO> getCertificateDTOSWithTags(List<GiftCertificateDTO> certificates) {
        if (certificates.isEmpty()) {
            log.error("[GiftCertificateService.findAll()] GiftCertificates not found");
            throw new GiftCertificateNotFoundException("GiftCertificates not found");
        } else {
            for (GiftCertificateDTO certificateDTO : certificates) {
                List<TagDTO> tagDTOS = getMappedAndCollected(certificateDTO);
                certificateDTO.setTags(new HashSet<>(tagDTOS));
            }
            log.debug("[GiftCertificateService.findAll()] GiftCertificates received from database: [{}]"
                    , certificates);
            return certificates;
        }
    }
}
