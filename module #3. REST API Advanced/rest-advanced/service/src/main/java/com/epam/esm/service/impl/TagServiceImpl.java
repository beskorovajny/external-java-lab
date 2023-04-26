package com.epam.esm.service.impl;

import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.exception.GiftCertificateNotFoundException;
import com.epam.esm.core.exception.TagAlreadyExistsException;
import com.epam.esm.core.exception.TagNotFoundException;
import com.epam.esm.core.model.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.MappingService;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements functionality of operating  {@link TagRepository} methods in according to received
 * parameters from TagController controller.
 * Using in {@link com.epam.esm.service.GiftCertificateService} and TagController classes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final MappingService<Tag, TagDTO> mappingService;

    @Override
    public void save(TagDTO tagDTO) throws TagAlreadyExistsException {
        Tag tag = mappingService.mapFromDto(tagDTO);
        log.debug("is exists tag : {}", tagRepository.isExists(tag));
        if (tagRepository.isExists(tag)) {
            log.error("[TagService.save()] Tag with given name:[{}] already exists.", tagDTO.getName());
            throw new TagAlreadyExistsException(String.format("Tag with given name:[%s] already exists.", tagDTO.getName()));
        }
        tagRepository.save(tag);
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
    public List<TagDTO> findAllByName(String name) {
        Validate.notBlank(name);
        List<TagDTO> tagDTO = tagRepository.findAllByName(name)
                .stream()
                .flatMap(Collection::stream)
                .map(mappingService::mapToDto)
                .toList();
        if (tagDTO.isEmpty()) {
            log.error("[TagService.findByName()] Tag for given name:[{}] not found", name);
            throw new TagNotFoundException(String.format("Tag not found (name:[%s])", name));
        } else {
            log.debug("[TagService.findByName()] Tag received from database: [{}], for name:[{}]", tagDTO, name);
            return tagDTO;
        }
    }

    @Override
    public List<TagDTO> findAll() {
        List<TagDTO> tags = tagRepository.findAll()
                .stream()
                .flatMap(Collection::stream)
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
    public void deleteById(Long id) {
        if (id == null || id < 1) {
            log.error("[TagService.deleteById()] An exception occurs: id:[{}] can't be less than zero", id);
            throw new IllegalArgumentException("Tag.id can't be less than zero.");
        }
        Optional<Tag> tag = tagRepository.findById(id);
        log.debug("Delete tag : {}" , tag);
        if (tag.isEmpty() || !tagRepository.isExists(tag.get())) {
            log.error("[TagService.deleteById()] Tag with given id:[{}] not found.", id);
            throw new TagNotFoundException(String.format("Tag with given id:[%d] not found for delete.", id));
        }
        tagRepository.deleteById(id);
        log.debug("[TagService.deleteById()] Tag for ID:[{}] removed", id);
    }
}
