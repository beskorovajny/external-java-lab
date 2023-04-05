package com.epam.esm.repository;

import com.epam.esm.model.Tag;

public interface TagRepository extends GenericRepository<Long, Tag> {
    boolean isExists(Tag tag);
}
