package com.epam.esm.core.model.query;

import lombok.Builder;
import lombok.Data;

/**
 * This class contains search and sort parameters to construct ready-to-use
 * database queries in QueryProvider class.
 * .
 */
@Data
@Builder
public class QueryParams {
    private String tagName;
    private String name;
    private String description;
    private String sortByName;
    private String sortByDate;
}
