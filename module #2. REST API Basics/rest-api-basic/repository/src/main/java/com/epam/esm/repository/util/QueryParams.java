package com.epam.esm.repository.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryParams {
    private String tagName;
    private String name;
    private String description;
    private String sortByName;
    private String sortByDate;
}
