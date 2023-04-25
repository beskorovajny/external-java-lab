/*
package com.epam.esm.repository.impl.jdbc;

import com.epam.esm.core.model.Tag;
import com.epam.esm.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagJDBCTemplate implements TagRepository {
    private static final String IS_EXISTS = "SELECT * FROM external_lab.tag WHERE name = ?";
    private static final String INSERT = "INSERT INTO external_lab.tag (name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM external_lab.tag WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM external_lab.tag WHERE name = ?";
    private static final String FIND_ALL_BY_NAME = "SELECT * FROM external_lab.tag WHERE name LIKE ?";
    private static final String FIND_ALL = "SELECT * FROM external_lab.tag";
    private static final String DELETE = "DELETE FROM external_lab.tag WHERE id = ?";
    private static final String FIND_ALL_BY_CERTIFICATE = "SELECT tag.id, tag.name FROM external_lab.tag LEFT JOIN" +
            " tag_has_gift_certificate on tag.id = tag_has_gift_certificate.tag_id WHERE gift_certificate_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean isExists(Tag tag) {
        Optional<Tag> tagOptional;
        try {
            tagOptional = Optional.ofNullable(jdbcTemplate.queryForObject(IS_EXISTS,
                    new BeanPropertyRowMapper<>(Tag.class), tag.getName()));
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return tagOptional.isPresent();
    }

    @Override
    public Long save(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(INSERT, new String[]{"id"});
            ps.setString(1, tag.getName());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        Optional<Tag> optionalTag;
        try {
            optionalTag = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    new BeanPropertyRowMapper<>(Tag.class), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return optionalTag;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        Optional<Tag> tag;
        try {
            tag = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    new BeanPropertyRowMapper<>(Tag.class), name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return tag;
    }

    @Override
    public Optional<List<Tag>> findAllByName(String name) {
        Optional<List<Tag>> optionalTags;
        try {
            optionalTags = Optional.of(jdbcTemplate.query(FIND_ALL_BY_NAME,
                    new BeanPropertyRowMapper<>(Tag.class), "%" + name + "%"));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return optionalTags;
    }

    public Optional<List<Tag>> findAllByCertificate(Long certificateId) {
        Optional<List<Tag>> optionalTags;
        try {
            optionalTags = Optional.of(jdbcTemplate.query(FIND_ALL_BY_CERTIFICATE,
                    new BeanPropertyRowMapper<>(Tag.class), certificateId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return optionalTags;
    }

    @Override
    public Optional<List<Tag>> findAll() {
        return Optional.of(jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Tag.class)));
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE, id);
    }
}
*/
