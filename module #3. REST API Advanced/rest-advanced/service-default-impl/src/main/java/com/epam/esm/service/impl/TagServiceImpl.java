package com.epam.esm.service.impl;

import com.epam.esm.service.pagination.PageableValidator;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.MappingService;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.exception.TagAlreadyExistsException;
import com.epam.esm.core.exception.TagNotFoundException;
import com.epam.esm.core.model.Pageable;
import com.epam.esm.core.model.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements functionality of operating  {@link TagRepository} methods in according to received
 * parameters from TagController controller.
 * Using in {@link GiftCertificateService} and TagController classes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final MappingService<Tag, TagDTO> mappingService;

    @Override
    public TagDTO save(TagDTO tagDTO) throws TagAlreadyExistsException {
        Tag tag = mappingService.mapFromDto(tagDTO);
        log.debug("is exists tag : {}", tagRepository.isExists(tag));
        if (tagRepository.isExists(tag)) {
            log.error("[TagService.save()] Tag with given name:[{}] already exists.", tagDTO.getName());
            throw new TagAlreadyExistsException(String.format("Tag with given name:[%s] already exists.", tagDTO.getName()));
        }
        return mappingService.mapToDto(tagRepository.save(tag));
    }

    @Override
    public TagDTO findById(Long id) {
        if (id == null || id < 1) {
            log.error("[TagService.findById()] An exception occurs: id:[{}] can't be less than zero or null", id);
            throw new IllegalArgumentException("An exception occurs: Tag.id can't be less than zero or null");
        }
        TagDTO tagDTO = tagRepository.findById(id)
                .map(mappingService::mapToDto)
                .orElseThrow(() -> {
                    log.error("[TagService.findById()] Tag for given ID:[{}] not found", id);
                    throw new TagNotFoundException(String.format("Tag not found (id:[%d])", id));
                });

        log.debug("[TagService.findById()] Tag received from database: [{}], for ID:[{}]", tagDTO, id);
        return tagDTO;
    }

    @Override
    public TagDTO findByName(String name) {
        Validate.notBlank(name);
        TagDTO tagDTO = tagRepository.findByName(name)
                .map(mappingService::mapToDto)
                .orElseThrow(() -> {
                    log.error("[TagService.findByName()] Tag for given name:[{}] not found", name);
                    throw new TagNotFoundException(String.format("Tag not found (name:[%s])", name));
                });

        log.debug("[TagService.findByName()] Tag received from database: [{}], for name:[{}]", tagDTO, name);
        return tagDTO;
    }

    @Override
    public List<TagDTO> findAllByCertificate(Long certificateId) {
        if (certificateId == null || certificateId < 1) {
            log.error("[TagService.findAllByCertificate()] An exception occurs: GiftCertificate.ID:[{}]" +
                    " can't be less than zero or null", certificateId);
            throw new IllegalArgumentException("An exception occurs: Tag.ID can't be less than zero or null");
        }
        List<TagDTO> tags = tagRepository.findAllByCertificate(certificateId)
                .stream()
                .map(mappingService::mapToDto)
                .toList();
        if (tags.isEmpty()) {
            log.error("[TagService.findAllByGiftCertificate()] Tags not found");
            throw new TagNotFoundException("Tags not found");
        }
        log.debug("[TagService.findAllByCertificate()] Tags received from database: [{}], for GiftCertificate.ID: [{}]",
                tags, certificateId);
        return tags;
    }

    @Override
    public List<TagDTO> findAll(Pageable pageable) {
        PageableValidator.validate(pageable);
        List<TagDTO> tags = tagRepository.findAll(PageableValidator.checkParams(pageable, tagRepository))
                .stream()
                .map(mappingService::mapToDto)
                .toList();
        if (tags.isEmpty()) {
            log.error("[TagService.findAll()] Tags not found");
            throw new TagNotFoundException("Tags not found");
        }
        log.debug("[TagService.findAll()] Tags received from database: [{}]", tags);
        return tags;
    }

    @Override
    public TagDTO deleteById(Long id) {
        if (id == null || id < 1) {
            log.error("[TagService.deleteById()] An exception occurs: id:[{}] can't be less than zero", id);
            throw new IllegalArgumentException("Tag.id can't be less than zero.");
        }
        Optional<Tag> tag = tagRepository.findById(id);
        log.debug("Delete tag : {}", tag);
        if (tag.isEmpty() || !tagRepository.isExists(tag.get())) {
            log.error("[TagService.deleteById()] Tag with given id:[{}] not found.", id);
            throw new TagNotFoundException(String.format("Tag with given id:[%d] not found for delete.", id));
        }

        log.debug("[TagService.deleteById()] Tag for ID:[{}] removed", id);
        return mappingService.mapToDto(tagRepository.deleteById(id));
    }
}
