package com.epam.esm.repository.utils;

import com.epam.esm.core.model.GiftCertificate;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * This class implements functionality of creating
 * database queries. Also query can be created
 * in according to {@link QueryParams} parameters received from
 * GiftCertificateController class
 */
@Slf4j
@NoArgsConstructor
@Component
@Setter
public class QueryProvider {
    private QueryParams queryParams;
    private static final String QUERY_PROVIDER = "[QueryProvider]";
    private static final String SELECT = "SELECT ";
    private static final String DISTINCT = " DISTINCT ";
    private static final String INSERT = "INSERT INTO ";
    private static final String DESC = " DESC";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String EQUAL = " = ";
    private static final String LIKE = " LIKE ";
    private static final String LOWER = " LOWER";
    private static final String COLON_SIGN = ":";
    private static final String FRONT_PERCENT_SIGN = "'%";
    private static final String BACK_PERCENT_SIGN = "%'";
    private static final String SINGLE_QUOTE = "'";
    private static final String COMMA = ", ";
    private static final String GC = "gc ";
    private static final String CERTIFICATE_FIELDS = "gc.id, gc.name," +
            " gc.description, gc.price, gc.duration," +
            " gc.create_date, gc.last_update_date ";
    private static final String FROM_GIFT_CERTIFICATE = "FROM GiftCertificate gc";
    private static final String CERTIFICATE_NAME = "gc.name";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String DESCRIPTION = "gift_certificate.description";
    private static final String CERTIFICATE_ID = "gift_certificate.id";
    private static final String TAG_ID = "tag.id";
    private static final String TAG_NAME = "tag.name";
    private static final String DATE = "gift_certificate.create_date";

    private static final String CERTIFICATE_VALUES = "external_lab.gift_certificate " +
            "(name, description, price, duration, create_date) VALUES (?, ?, ?, ?, ?)";
    private static final String GIFT_CERTIFICATE_HAS_TAG_VALUES = "gift_certificate_has_tag(gift_certificate_id, tag_id)" +
            " VALUES (?, ?)";

    private static final String UPDATE = "UPDATE external_lab.gift_certificate SET ";
    private static final String UPD_NAME = "name = ";
    private static final String UPD_DESCRIPTION = "description = ";
    private static final String UPD_PRICE = "price = ";
    private static final String UPD_DURATION = "duration = ";
    private static final String UPD_LAST_UPDATE_DATE = "last_update_date = ";
    private static final String DELETE = "DELETE FROM external_lab.gift_certificate";
    private static final String JOIN_GIFT_CERTIFICATE_HAS_TAG = " LEFT JOIN gift_certificate_has_tag on" +
            " gc.id = gift_certificate_has_tag.gift_certificate_id";
    private static final String JOIN_TAG = " LEFT JOIN tag on tag.id = gift_certificate_has_tag.tag_id";

    public String isExists() {
        log.debug("{} IS_EXISTS query returned.", QUERY_PROVIDER);
        return SELECT + GC + FROM_GIFT_CERTIFICATE + WHERE +
                CERTIFICATE_NAME + EQUAL + COLON_SIGN + NAME;
    }

    public String insert() {
        log.debug("{} INSERT query returned.", QUERY_PROVIDER);
        return INSERT + CERTIFICATE_VALUES;
    }

    public String attachCertificateToTag() {
        log.debug("{} ATTACH_TAG_TO_CERTIFICATE query returned.", QUERY_PROVIDER);
        return INSERT + GIFT_CERTIFICATE_HAS_TAG_VALUES;
    }

    public String findById() {
        log.debug("{} FIND_BY_ID query returned.", QUERY_PROVIDER);
        return SELECT + GC + FROM_GIFT_CERTIFICATE + WHERE + CERTIFICATE_ID + EQUAL + COLON_SIGN + ID;
    }

    public String findAll() {
        log.debug("{} FIND_ALL query returned.", QUERY_PROVIDER);
        return SELECT + GC + FROM_GIFT_CERTIFICATE;
    }

    public String findAllByName() {
        log.debug("{} FIND_ALL_BY_NAME query returned.", QUERY_PROVIDER);
        return SELECT + GC + FROM_GIFT_CERTIFICATE + WHERE + LOWER
                + "(" + CERTIFICATE_NAME + ")" + LIKE + LOWER + "(" +
                COLON_SIGN + NAME + ")";
    }

    /**
     * This method return database query for update operation
     * depends on available fields of GiftCertificate class.
     * Field will be skipped if value is equal to null or empty.
     *
     * @param giftCertificate contains new field values for update
     * @return constructed query
     */
    public String update(GiftCertificate giftCertificate) {
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
        sb.append(WHERE).append(CERTIFICATE_ID).append(EQUAL).append(giftCertificate.getId());
        log.debug("{} UPDATE query returned.", QUERY_PROVIDER);
        return sb.toString();
    }

    public String delete() {
        log.debug("{} DELETE query returned.", QUERY_PROVIDER);
        return DELETE + WHERE + CERTIFICATE_ID + EQUAL + COLON_SIGN + ID;
    }

    /**
     * This method implements functionality of constructing
     * database query based on {@link QueryParams} fields values.
     * Result query will use to search and sort {@link GiftCertificate} objects
     * with search by received parameters option.
     *
     * @return ready to use database query
     */
    public String findAllWithParams() {
        StringBuilder sb = new StringBuilder().append(SELECT).append(GC)/*.append(DISTINCT).append(CERTIFICATE_FIELDS)*/
                .append(FROM_GIFT_CERTIFICATE);
        if (queryParams.getTagName() != null && !queryParams.getTagName().isEmpty()) {
            sb.append(JOIN_GIFT_CERTIFICATE_HAS_TAG).append(JOIN_TAG).append(WHERE).append(TAG_NAME)
                    .append(LIKE).append("(:").append(queryParams.getTagName()).append(")");
            getQueryDependsOnParams(sb, AND);
        } else {
            getQueryDependsOnParams(sb, WHERE);
        }
        log.debug("FIND_ALL_WITH_PARAMS: {}" , sb);
        return sb.toString();
    }

    /**
     * This method implements functionality of adding necessary
     * search operations to database query. Depends on values of {@link QueryParams#getTagName()}
     * , {@link QueryParams#getName()} ()} and {@link QueryParams#getDescription()}
     * parameters received from GiftCertificateController
     *
     * @param sb constructed query from {@link #findAllWithParams()}  method.
     */
    private void getQueryDependsOnParams(StringBuilder sb, String statement) {
        if ((queryParams.getName() != null && !queryParams.getName().isEmpty())
                && (queryParams.getDescription() != null && !queryParams.getDescription().isEmpty())) {
            sb.append(statement).append(CERTIFICATE_NAME).append(LIKE).append("(:")
                    .append(queryParams.getName()).append(")")
                    .append(AND).append(DESCRIPTION).append(LIKE).append("(:")
                    .append(queryParams.getDescription()).append(")");
            getSortedByParam(sb);
        } else if ((queryParams.getName() != null && !queryParams.getName().isEmpty())
                && (queryParams.getDescription() == null || queryParams.getDescription().isEmpty())) {
            sb.append(statement).append(CERTIFICATE_NAME).append(LIKE).append("(:")
                    .append(queryParams.getName())
                    .append(")");
            getSortedByParam(sb);
        } else if ((queryParams.getDescription() != null && !queryParams.getDescription().isEmpty())
                && (queryParams.getName() == null || queryParams.getName().isEmpty())) {
            sb.append(statement).append(DESCRIPTION).append(LIKE).append("(:")
                    .append(queryParams.getDescription()).append(")");
            getSortedByParam(sb);
        } else {
            getSortedByParam(sb);
        }
    }

    /**
     * This method implements functionality of adding necessary
     * sorting operations to database query. Depends on values of {@link QueryParams#getSortByDate()}
     * and {@link QueryParams#getSortByName()} parameters received from GiftCertificateController
     *
     * @param sb constructed query from {@link #getQueryDependsOnParams(StringBuilder sb, String statement)} method.
     */
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
