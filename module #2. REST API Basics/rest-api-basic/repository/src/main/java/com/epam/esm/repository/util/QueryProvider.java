package com.epam.esm.repository.util;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@Setter
public class QueryProvider {
    private QueryParams queryParams;
    private static final String SELECT = "SELECT gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date ";
    private static final String FROM_GIFT_CERTIFICATE = "FROM external_lab.gift_certificate";
    private static final String CERTIFICATE_NAME = "gift_certificate.name";
    private static final String DESCRIPTION = "gift_certificate.description";
    private static final String CERTIFICATE_ID = "gift_certificate.id";
    private static final String TAG_ID = "tag.id";
    private static final String TAG_NAME = "tag.name";
    private static final String DATE = "gift_certificate.create_date";
    private static final String DESC = " DESC";
    private static final String ASC = " ASC";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String EQUAL = " = ";

    private static final String LIKE = " LIKE ";
    private static final String QUESTION_SIGN = "?";
    private static final String FRONT_PERCENT_SIGN = "'%";
    private static final String BACK_PERCENT_SIGN = "%'";

    private static final String INSERT = "INSERT INTO external_lab.gift_certificate " +
            "(name, description, price, duration, create_date) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE external_lab.gift_certificate SET name = ?, description = ?,"
            + "price = ?, duration = ?, last_update_date = ?";
    private static final String DELETE = "DELETE FROM external_lab.gift_certificate";
    private static final String JOIN_TAG_HAS_CERTIFICATE = " LEFT JOIN tag_has_gift_certificate on gift_certificate.id =" +
            " tag_has_gift_certificate.gift_certificate_id";
    private static final String JOIN_TAG = " LEFT JOIN tag on tag.id = tag_has_gift_certificate.tag_id";

   /* private static final String FIND_BY_ID = "SELECT * FROM external_lab.gift_certificate WHERE id = ?";
    private static final String FIND_ALL_BY_NAME = "SELECT * FROM external_lab.gift_certificate WHERE name LIKE ?";*/
    /*private static final String FIND_ALL_BY_DESCRIPTION = "SELECT * FROM external_lab.gift_certificate WHERE " +
            "description LIKE ?";*/
    /*private static final String FIND_ALL = "SELECT * FROM external_lab.gift_certificate";*/


    /*private static final String FIND_ALL_BY_TAG_AND_NAME = "SELECT gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date FROM external_lab.gift_certificate" +
            " LEFT JOIN tag_has_gift_certificate on gift_certificate.id = tag_has_gift_certificate.gift_certificate_id" +
            " WHERE tag_id = ? AND gift_certificate.name LIKE ?";

    private static final String FIND_ALL_BY_TAG_AND_DESCRIPTION = "SELECT gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date FROM external_lab.gift_certificate" +
            " LEFT JOIN tag_has_gift_certificate on gift_certificate.id = tag_has_gift_certificate.gift_certificate_id" +
            " WHERE tag_id = ? AND gift_certificate.description LIKE ?";*/

    public String isExists() {
        return SELECT + FROM_GIFT_CERTIFICATE + WHERE + CERTIFICATE_NAME + EQUAL + QUESTION_SIGN;
    }

    public String insert() {
        return INSERT;
    }

    public String findById() {
        return SELECT + FROM_GIFT_CERTIFICATE + WHERE + CERTIFICATE_ID + EQUAL + QUESTION_SIGN;
    }

    public String findAll() {
        return SELECT + FROM_GIFT_CERTIFICATE;
    }

    public String findAllByName() {
        return SELECT + FROM_GIFT_CERTIFICATE + WHERE + CERTIFICATE_NAME + LIKE + QUESTION_SIGN;
    }

    public String update() {
        return UPDATE + WHERE + CERTIFICATE_ID + EQUAL + QUESTION_SIGN;
    }

    public String delete() {
        return DELETE + WHERE + CERTIFICATE_ID + EQUAL + QUESTION_SIGN;
    }

    public String findAllWithParams() {
        StringBuilder sb = new StringBuilder().append(SELECT).append(FROM_GIFT_CERTIFICATE);
        if (queryParams.getTagName() != null && !queryParams.getTagName().isEmpty()) {
            sb.append(JOIN_TAG_HAS_CERTIFICATE).append(JOIN_TAG).append(WHERE).append(TAG_NAME)
                    .append(LIKE).append(FRONT_PERCENT_SIGN).append(queryParams.getTagName()).append(BACK_PERCENT_SIGN);
            paramsHelper(sb, AND);
        } else {
            paramsHelper(sb, WHERE);
        }
        return sb.toString();
    }

    private void paramsHelper(StringBuilder sb, String param) {
        if (queryParams.getName() != null && !queryParams.getName().isEmpty()) {
            sb.append(param).append(CERTIFICATE_NAME).append(LIKE).append(FRONT_PERCENT_SIGN)
                    .append(queryParams.getName())
                    .append(BACK_PERCENT_SIGN);
            getSortedByParam(sb);
        } else if (queryParams.getDescription() != null && !queryParams.getDescription().isEmpty()) {
            sb.append(param).append(DESCRIPTION).append(LIKE).append(FRONT_PERCENT_SIGN)
                    .append(queryParams.getDescription()).append(BACK_PERCENT_SIGN);
            getSortedByParam(sb);
        }
        else {
            getSortedByParam(sb);
        }
    }

    private void getSortedByParam(StringBuilder sb) {
        if (queryParams.getSortByName() != null && !queryParams.getSortByName().isEmpty()) {
            sb.append(ORDER_BY).append(CERTIFICATE_NAME);
            if (queryParams.getSortByName().equalsIgnoreCase(DESC.trim()))
                sb.append(DESC);
        } else if (queryParams.getSortByDate() != null && !queryParams.getSortByDate().isEmpty()) {
            sb.append(ORDER_BY).append(DATE);
            if (queryParams.getSortByDate().equalsIgnoreCase(DESC.trim()))
                sb.append(DESC);
        }
    }
}
