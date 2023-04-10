package com.epam.esm.repository;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends GenericRepository<Long, Tag> {
    boolean isExists(TagDTO tag);
    Optional<List<Tag>> findAllByCertificate(Long certificateId);
}
