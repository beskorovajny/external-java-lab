package com.epam.esm.repository.impl.jdbctemplate.mysql;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.util.QueryParams;
import com.epam.esm.repository.util.QueryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GiftCertificateJDBCTemplate implements GiftCertificateRepository {

    private static final String IS_EXISTS = "SELECT * FROM external_lab.gift_certificate WHERE name = ?";
    private static final String INSERT = "INSERT INTO external_lab.gift_certificate " +
            "(name, description, price, duration, create_date) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM external_lab.gift_certificate WHERE id = ?";
    private static final String FIND_ALL_BY_NAME = "SELECT * FROM external_lab.gift_certificate WHERE name LIKE ?";
    private static final String FIND_ALL_BY_DESCRIPTION = "SELECT * FROM external_lab.gift_certificate WHERE " +
            "description LIKE ?";
    private static final String FIND_ALL = "SELECT * FROM external_lab.gift_certificate";
    private static final String UPDATE = "UPDATE external_lab.gift_certificate SET name = ?, description = ?,"
            + "price = ?, duration = ?, last_update_date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM external_lab.gift_certificate WHERE id = ?";
    private static final String FIND_ALL_BY_TAG = "SELECT gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date FROM external_lab.gift_certificate" +
            " LEFT JOIN tag_has_gift_certificate on gift_certificate.id = tag_has_gift_certificate.gift_certificate_id" +
            " WHERE tag_id = ?";

    private static final String FIND_ALL_BY_TAG_AND_NAME = "SELECT gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date FROM external_lab.gift_certificate" +
            " LEFT JOIN tag_has_gift_certificate on gift_certificate.id = tag_has_gift_certificate.gift_certificate_id" +
            " WHERE tag_id = ? AND gift_certificate.name LIKE ?";

    private static final String FIND_ALL_BY_TAG_AND_DESCRIPTION = "SELECT gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date FROM external_lab.gift_certificate" +
            " LEFT JOIN tag_has_gift_certificate on gift_certificate.id = tag_has_gift_certificate.gift_certificate_id" +
            " WHERE tag_id = ? AND gift_certificate.description LIKE ?";

    private final JdbcTemplate jdbcTemplate;
    private final QueryProvider queryProvider;

    @Override
    public boolean isExists(GiftCertificateDTO giftCertificate) {
        Optional<GiftCertificate> certificate;
        try {
            certificate = Optional.ofNullable(jdbcTemplate.queryForObject(queryProvider.isExists(),
                    new BeanPropertyRowMapper<>(GiftCertificate.class), giftCertificate.getName()));
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return certificate.isPresent();
    }

    @Override
    public Long save(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(queryProvider.insert(), new String[]{"id"});
            ps.setString(1, giftCertificate.getName());
            ps.setString(2, giftCertificate.getDescription());
            ps.setBigDecimal(3, BigDecimal.valueOf(giftCertificate.getPrice()));
            ps.setInt(4, giftCertificate.getDuration());
            ps.setTimestamp(5, Timestamp.valueOf(giftCertificate.getCreateDate()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        Optional<GiftCertificate> certificate;
        try {
            certificate = Optional.ofNullable(jdbcTemplate.queryForObject(queryProvider.findById(),
                    new BeanPropertyRowMapper<>(GiftCertificate.class), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return certificate;
    }

    public Optional<List<GiftCertificate>> findByName(String name) {
        return getGiftCertificates(name, queryProvider.findAllByName());
    }
    @Override
    public Optional<List<GiftCertificate>> findAll() {
        return Optional.of(jdbcTemplate.query(queryProvider.findAll(),
                new BeanPropertyRowMapper<>(GiftCertificate.class)));
    }

    @Override
    public Optional<List<GiftCertificate>> findAllWithParams(QueryParams queryParams) {
        queryProvider.setQueryParams(queryParams);
        return Optional.of(jdbcTemplate.query(queryProvider.findAllWithParams()
                , new BeanPropertyRowMapper<>(GiftCertificate.class)));
    }

    @Override
    public void update(Long id, GiftCertificate giftCertificate) {
        jdbcTemplate.update(UPDATE, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(), giftCertificate.getLastUpdateDate(),
                id);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(queryProvider.delete(), id);
    }

    private Optional<List<GiftCertificate>> getGiftCertificates(String option, String query) {
        Optional<List<GiftCertificate>> certificates;
        try {
            certificates = Optional.of(jdbcTemplate.query(query,
                    new BeanPropertyRowMapper<>(GiftCertificate.class), "%" + option + "%"));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return certificates;
    }

    private Optional<List<GiftCertificate>> getGiftCertificatesWithTagId(Long tagId, String option, String query) {
        Optional<List<GiftCertificate>> certificates;

        try {
            certificates = Optional.of(jdbcTemplate.query(query,
                    new BeanPropertyRowMapper<>(GiftCertificate.class), tagId, "%" + option + "%"));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return certificates;
    }
}
