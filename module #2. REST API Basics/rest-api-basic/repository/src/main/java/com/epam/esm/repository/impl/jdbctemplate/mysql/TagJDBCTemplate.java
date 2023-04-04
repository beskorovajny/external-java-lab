package com.epam.esm.repository.impl.jdbctemplate.mysql;

import com.epam.esm.model.Tag;
import com.epam.esm.repository.TagRepository;
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
public class TagJDBCTemplate implements TagRepository {
    private static final String INSERT = "INSERT INTO external_lab.tag (name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM external_lab.tag WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM external_lab.tag WHERE name = ?";
    private static final String FIND_ALL = "SELECT * FROM external_lab.tag";
    private static final String DELETE = "DELETE FROM external_lab.tag WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    public TagJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isExists(Tag tag) {
        return false;
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
        Optional<Tag> optionalTag;
        try {
            optionalTag = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    new BeanPropertyRowMapper<>(Tag.class), name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return optionalTag;
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
