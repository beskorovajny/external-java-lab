package com.epam.esm.repository.util;

import com.epam.esm.model.GiftCertificate;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@NoArgsConstructor
@Component
@Setter
public class QueryProvider {
    private QueryParams queryParams;
    private static final String  QUERY_PROVIDER = "[QueryProvider]";
    private static final String SELECT = "SELECT ";
    private static final String DISTINCT = " DISTINCT ";
    private static final String INSERT = "INSERT INTO ";
    private static final String DESC = " DESC";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String EQUAL = " = ";
    private static final String LIKE = " LIKE ";
    private static final String QUESTION_SIGN = "?";
    private static final String FRONT_PERCENT_SIGN = "'%";
    private static final String BACK_PERCENT_SIGN = "%'";
    private static final String SINGLE_QUOTE = "'";
    private static final String COMMA = ", ";
    private static final String CERTIFICATE_FIELDS = "gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date ";
    private static final String FROM_GIFT_CERTIFICATE = "FROM external_lab.gift_certificate";
    private static final String CERTIFICATE_NAME = "gift_certificate.name";
    private static final String DESCRIPTION = "gift_certificate.description";
    private static final String CERTIFICATE_ID = "gift_certificate.id";
    private static final String TAG_ID = "tag.id";
    private static final String TAG_NAME = "tag.name";
    private static final String DATE = "gift_certificate.create_date";

    private static final String CERTIFICATE_VALUES = "external_lab.gift_certificate " +
            "(name, description, price, duration, create_date) VALUES (?, ?, ?, ?, ?)";
    private static final String TAG_HAS_CERTIFICATE_VALUES = "tag_has_gift_certificate(tag_id, gift_certificate_id)" +
            " VALUES (?, ?)";

    private static final String UPDATE = "UPDATE external_lab.gift_certificate SET ";
    private static final String UPD_NAME = "name = ";
    private static final String UPD_DESCRIPTION = "description = ";
    private static final String UPD_PRICE = "price = ";
    private static final String UPD_DURATION = "duration = ";
    private static final String UPD_LAST_UPDATE_DATE = "last_update_date = ";
    private static final String DELETE = "DELETE FROM external_lab.gift_certificate";
    private static final String JOIN_TAG_HAS_CERTIFICATE = " LEFT JOIN tag_has_gift_certificate on" +
            " gift_certificate.id = tag_has_gift_certificate.gift_certificate_id";
    private static final String JOIN_TAG = " LEFT JOIN tag on tag.id = tag_has_gift_certificate.tag_id";

    public String isExists() {
        log.debug("{} IS_EXISTS query returned.", QUERY_PROVIDER);
        return SELECT + CERTIFICATE_FIELDS + FROM_GIFT_CERTIFICATE + WHERE + CERTIFICATE_NAME + EQUAL + QUESTION_SIGN;
    }

    public String insert() {
        log.debug("{} INSERT query returned.", QUERY_PROVIDER);
        return INSERT + CERTIFICATE_VALUES;
    }

    public String attachCertificateToTag() {
        log.debug("{} ATTACH_TAG_TO_CERTIFICATE query returned.", QUERY_PROVIDER);
        return INSERT + TAG_HAS_CERTIFICATE_VALUES;
    }

    public String findById() {
        log.debug("{} FIND_BY_ID query returned.", QUERY_PROVIDER);
        return SELECT + CERTIFICATE_FIELDS + FROM_GIFT_CERTIFICATE + WHERE + CERTIFICATE_ID + EQUAL + QUESTION_SIGN;
    }

    public String findAll() {
        log.debug("{} FIND_ALL query returned.", QUERY_PROVIDER);
        return SELECT + CERTIFICATE_FIELDS + FROM_GIFT_CERTIFICATE;
    }

    public String findAllByName() {
        log.debug("{} FIND_ALL_BY_NAME query returned.", QUERY_PROVIDER);
        return SELECT + CERTIFICATE_FIELDS + FROM_GIFT_CERTIFICATE + WHERE + CERTIFICATE_NAME + LIKE + QUESTION_SIGN;
    }

    public String update(Long id, GiftCertificate giftCertificate) {
        StringBuilder sb = new StringBuilder().append(UPDATE);
        if (giftCertificate.getName() != null && !giftCertificate.getName().isEmpty()) {
            log.debug("{} UPD_NAME query concatenated.", QUERY_PROVIDER);
            sb.append(UPD_NAME).append(SINGLE_QUOTE).append(giftCertificate.getName()).append(SINGLE_QUOTE);
        }
        if (giftCertificate.getDescription() != null && !giftCertificate.getDescription().isEmpty()) {
            log.debug("{} UPD_DESCRIPTION query concatenated.", QUERY_PROVIDER);
            sb.append(COMMA).append(UPD_DESCRIPTION).append(SINGLE_QUOTE)
                    .append(giftCertificate.getDescription()).append(SINGLE_QUOTE);
        }
        if (giftCertificate.getPrice() != null) {
            log.debug("{} UPD_PRICE query concatenated.", QUERY_PROVIDER);
            sb.append(COMMA).append(UPD_PRICE).append(giftCertificate.getPrice());
        }
        if (giftCertificate.getDuration() != null) {
            log.debug("{} UPD_DURATION query concatenated.", QUERY_PROVIDER);
            sb.append(COMMA).append(UPD_DURATION).append(giftCertificate.getDuration());
        }
        if (giftCertificate.getLastUpdateDate() != null) {
            log.debug("{} UPD_LAST_UPDATE_DATE query concatenated.", QUERY_PROVIDER);
            sb.append(COMMA).append(UPD_LAST_UPDATE_DATE).append(SINGLE_QUOTE)
                    .append(Timestamp.valueOf(giftCertificate.getLastUpdateDate())).append(SINGLE_QUOTE);
        }
        sb.append(WHERE).append(CERTIFICATE_ID).append(EQUAL).append(id);
        log.debug("{} UPDATE query returned.", QUERY_PROVIDER);
        return sb.toString();
    }

    public String delete() {
        log.debug("{} DELETE query returned.", QUERY_PROVIDER);
        return DELETE + WHERE + CERTIFICATE_ID + EQUAL + QUESTION_SIGN;
    }

    public String findAllWithParams() {
        StringBuilder sb = new StringBuilder().append(SELECT).append(DISTINCT).append(CERTIFICATE_FIELDS)
                .append(FROM_GIFT_CERTIFICATE);
        if (queryParams.getTagName() != null && !queryParams.getTagName().isEmpty()) {
            sb.append(JOIN_TAG_HAS_CERTIFICATE).append(JOIN_TAG).append(WHERE).append(TAG_NAME)
                    .append(LIKE).append(FRONT_PERCENT_SIGN).append(queryParams.getTagName()).append(BACK_PERCENT_SIGN);
            getQueryDependsOnParams(sb, AND);
        } else {
            getQueryDependsOnParams(sb, WHERE);
        }
        return sb.toString();
    }

    private void getQueryDependsOnParams(StringBuilder sb, String statement) {
        if ((queryParams.getName() != null && !queryParams.getName().isEmpty())
                && (queryParams.getDescription() != null && !queryParams.getDescription().isEmpty())) {
            sb.append(statement).append(CERTIFICATE_NAME).append(LIKE).append(FRONT_PERCENT_SIGN)
                    .append(queryParams.getName()).append(BACK_PERCENT_SIGN)
                    .append(AND).append(DESCRIPTION).append(LIKE).append(FRONT_PERCENT_SIGN)
                    .append(queryParams.getDescription()).append(BACK_PERCENT_SIGN);
        } else if ((queryParams.getName() != null && !queryParams.getName().isEmpty())
                && (queryParams.getDescription() == null || queryParams.getDescription().isEmpty())) {
            sb.append(statement).append(CERTIFICATE_NAME).append(LIKE).append(FRONT_PERCENT_SIGN)
                    .append(queryParams.getName())
                    .append(BACK_PERCENT_SIGN);
            getSortedByParam(sb);
        } else if ((queryParams.getDescription() != null && !queryParams.getDescription().isEmpty())
                && (queryParams.getName() == null || queryParams.getName().isEmpty())) {
            sb.append(statement).append(DESCRIPTION).append(LIKE).append(FRONT_PERCENT_SIGN)
                    .append(queryParams.getDescription()).append(BACK_PERCENT_SIGN);
            getSortedByParam(sb);
        } else {
            getSortedByParam(sb);
        }
    }

    private void getSortedByParam(StringBuilder sb) {
        if ((queryParams.getSortByName() != null && !queryParams.getSortByName().isEmpty())
                && (queryParams.getSortByDate() == null || queryParams.getSortByDate().isEmpty())) {
            sb.append(ORDER_BY).append(CERTIFICATE_NAME);
            if (queryParams.getSortByName().equalsIgnoreCase(DESC.trim()))
                sb.append(DESC);
        } else if ((queryParams.getSortByDate() != null && !queryParams.getSortByDate().isEmpty())
                && queryParams.getSortByName() == null || queryParams.getSortByName().isEmpty()) {
            sb.append(ORDER_BY).append(DATE);
            if (queryParams.getSortByDate().equalsIgnoreCase(DESC.trim()))
                sb.append(DESC);
        }
    }
}
