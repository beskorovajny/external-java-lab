package com.epam.esm.repository.impl.jdbctemplate.mysql;

import com.epam.esm.model.Tag;
import com.epam.esm.repository.TagRepository;
import org.springframework.beans.factory.annotation.Qualifier;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.dao.EmptyResultDataAccessException;
=======
>>>>>>> a5f4ed9 (refactored to multi-module structure)
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
=======
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
<<<<<<< HEAD
import org.springframework.jdbc.core.PreparedStatementCreator;
>>>>>>> 8368e76 (log4j2 configured, dto/dto <-> model mapping/tag service created)
=======
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.PreparedStatement;
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
>>>>>>> 8368e76 (log4j2 configured, dto/dto <-> model mapping/tag service created)
=======
import java.sql.PreparedStatement;
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TagJDBCTemplate implements TagRepository {
    private static final String INSERT = "INSERT INTO tag (name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM tag WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM tag WHERE name = ?";
    private static final String FIND_ALL = "SELECT * FROM tag";
    private static final String DELETE = "DELETE FROM tag WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    public TagJDBCTemplate(@Qualifier("prodJDBCTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isExists(Tag tag) {
        return false;
    }

    @Override
    public Long save(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
            ps.setString(1, tag.getName());
            return ps;
<<<<<<< HEAD
=======
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps =
                        connection.prepareStatement(INSERT, new String[]{"id"});
                ps.setString(1, tag.getName());
                return ps;
            }
>>>>>>> 8368e76 (log4j2 configured, dto/dto <-> model mapping/tag service created)
=======
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Tag.class), id));
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
