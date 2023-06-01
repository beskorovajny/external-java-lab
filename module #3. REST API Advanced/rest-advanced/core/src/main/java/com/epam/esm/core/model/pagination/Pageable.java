package com.epam.esm.core.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pageable {
    private Integer page;
    private Integer pageSize;
}
