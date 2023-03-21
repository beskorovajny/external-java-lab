package com.epam.esm.repository.impl.jdbctemplate.mysql;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.repository.GiftCertificateRepository;
import org.springframework.beans.factory.annotation.Qualifier;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.dao.EmptyResultDataAccessException;
=======
>>>>>>> a5f4ed9 (refactored to multi-module structure)
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
<<<<<<< HEAD
=======
import org.springframework.jdbc.core.PreparedStatementCreator;
>>>>>>> 8368e76 (log4j2 configured, dto/dto <-> model mapping/tag service created)
=======
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.Timestamp;
=======
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
>>>>>>> 8368e76 (log4j2 configured, dto/dto <-> model mapping/tag service created)
=======
import java.sql.PreparedStatement;
import java.sql.Timestamp;
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class GiftCertificateJDBCTemplate implements GiftCertificateRepository {
    private static final String INSERT = "INSERT INTO gift_certificate " +
            "(name, description, price, duration, create_date) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM gift_certificate WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM gift_certificate WHERE name = ?";
    private static final String FIND_ALL = "SELECT * FROM gift_certificate";
    private static final String UPDATE = "UPDATE gift_certificate SET name = ?, description = ?,"
            + "price = ?, duration = ?, last_update_date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM gift_certificate WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateJDBCTemplate(@Qualifier("prodJDBCTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isExists(GiftCertificate giftCertificate) {
        return false;
    }

    @Override
    public Long save(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
            ps.setString(1, giftCertificate.getName());
            ps.setString(2, giftCertificate.getDescription());
            ps.setBigDecimal(3, BigDecimal.valueOf(giftCertificate.getPrice()));
            ps.setInt(4, giftCertificate.getDuration());
            ps.setTimestamp(5, Timestamp.valueOf(giftCertificate.getCreateDate()));
            return ps;
<<<<<<< HEAD
=======
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps =
                        connection.prepareStatement(INSERT, new String[]{"id"});
                ps.setString(1, giftCertificate.getName());
                ps.setString(2, giftCertificate.getDescription());
                ps.setBigDecimal(3, BigDecimal.valueOf(giftCertificate.getPrice()));
                ps.setInt(4, giftCertificate.getDuration());
                ps.setDate(5, Date.valueOf(String.valueOf(giftCertificate.getCreateDate())));
                return ps;
            }
>>>>>>> 8368e76 (log4j2 configured, dto/dto <-> model mapping/tag service created)
=======
>>>>>>> 36fc8d4 (gift certificate service created, properties files updated)
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                new BeanPropertyRowMapper<>(GiftCertificate.class), id));
    }

    public Optional<GiftCertificate> findByName(String name) {
        Optional<GiftCertificate> giftCertificate;
        try {
            giftCertificate = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    new BeanPropertyRowMapper<>(GiftCertificate.class), name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return giftCertificate;
    }

    @Override
    public Optional<List<GiftCertificate>> findAll() {
        return Optional.of(jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(GiftCertificate.class)));
    }

    @Override
    public void update(Long id, GiftCertificate giftCertificate) {
        jdbcTemplate.update(UPDATE, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(), giftCertificate.getLastUpdateDate(),
                id);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE, id);
    }
}
