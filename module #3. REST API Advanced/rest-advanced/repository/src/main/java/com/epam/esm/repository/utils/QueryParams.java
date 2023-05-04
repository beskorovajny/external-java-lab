package com.epam.esm.repository.utils;

import lombok.Builder;
import lombok.Data;

/**
 * This class contains search and sort parameters to construct ready-to-use
 * database queries in {@link QueryProvider} class.
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
