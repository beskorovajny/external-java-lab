package com.epam.esm.repository.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryProvider {
    private QueryParams queryParams;
    private static final String SELECT = "SELECT gift_certificate.id, gift_certificate.name," +
            " gift_certificate.description, gift_certificate.price, gift_certificate.duration," +
            " gift_certificate.create_date, gift_certificate.last_update_date ";
    private static final String FROM_GIFT_CERTIFICATE = "FROM external_lab.gift_certificate";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String NAME_EQUALS = "name = ?";
    private static final String ID_EQUALS = "id = ?";
    private static final String NAME_LIKE = "gift_certificate.name LIKE ?";
    private static final String DESCRIPTION_LIKE = "gift_certificate.description LIKE ?";

    /*private static final String IS_EXISTS = "SELECT * FROM external_lab.gift_certificate WHERE name = ?";*/
    private static final String INSERT = "INSERT INTO external_lab.gift_certificate " +
            "(name, description, price, duration, create_date) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE external_lab.gift_certificate SET name = ?, description = ?,"
            + "price = ?, duration = ?, last_update_date = ?";
    private static final String DELETE = "DELETE FROM external_lab.gift_certificate";
    private static final String BY_TAG = " LEFT JOIN tag_has_gift_certificate on gift_certificate.id =" +
            " tag_has_gift_certificate.gift_certificate_id";
    private static final String TAG_ID_EQUAL = "tag_id = ?";

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
        return SELECT + FROM_GIFT_CERTIFICATE + WHERE + NAME_EQUALS;
    }

    public String insert() {
        return INSERT;
    }

    public String findById() {
        return SELECT + FROM_GIFT_CERTIFICATE + WHERE + ID_EQUALS;
    }

    public String findAllByName() {
        return SELECT + FROM_GIFT_CERTIFICATE + WHERE + NAME_LIKE;
    }

    public String findAllByDescription() {
        return SELECT + FROM_GIFT_CERTIFICATE + WHERE + DESCRIPTION_LIKE;
    }

    public String findAll() {
        return SELECT + FROM_GIFT_CERTIFICATE;
    }

    public String update() {
        return UPDATE + WHERE + ID_EQUALS;
    }

    public String delete() {
        return DELETE + WHERE + ID_EQUALS;
    }

    public String findAllByTag() {
        return SELECT + FROM_GIFT_CERTIFICATE + BY_TAG + WHERE + TAG_ID_EQUAL;
    }
    public String findAllByTagAndName() {
        return SELECT + FROM_GIFT_CERTIFICATE + BY_TAG + WHERE + TAG_ID_EQUAL + AND + NAME_LIKE;
    }

    public String findAllByTagAndDescription() {
        return SELECT + FROM_GIFT_CERTIFICATE + BY_TAG + WHERE + TAG_ID_EQUAL + AND + DESCRIPTION_LIKE;
    }


}
