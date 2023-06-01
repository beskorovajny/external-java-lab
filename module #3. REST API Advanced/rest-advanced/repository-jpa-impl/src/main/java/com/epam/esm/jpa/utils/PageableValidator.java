package com.epam.esm.jpa.utils;

import com.epam.esm.core.model.pagination.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

@Slf4j
public class PageableValidator {
    private PageableValidator() {
    }

    public static void validate(Pageable pageable) {
        Validate.notNull(pageable, "Pageable can't be null!");
        Validate.notNull(pageable.getPage(), "Pageable.page can't be null!");
        Validate.notNull(pageable.getPageSize(), "Pageable.pageSize can't be null!");
    }

    public static Pageable checkParams(Pageable pageable, Long totalRecords) {
        int lastPage = getLastPage(pageable, totalRecords);
        if (pageable.getPage() < 1) {
            pageable.setPage(1);
        }
        if (pageable.getPage() > getLastPage(pageable, totalRecords)) {
            pageable.setPage(lastPage);
        }
        return pageable;
    }


    public static int getLastPage(Pageable pageable, Long totalRecords) {
        if (totalRecords == null || totalRecords <= 0) {
            log.error("[PageableValidator.getLastPage()] No records found...");
            throw new IllegalArgumentException("[PageableValidator.getLastPage()] No records found...");
        }
        if (totalRecords % pageable.getPageSize() == 0) {
            return (int) (totalRecords / pageable.getPageSize());
        } else {
            return (int) (totalRecords / pageable.getPageSize()) + 1;
        }
    }

    public static int getFirstResultValue(Pageable pageable) {
        if (pageable == null) {
            log.error("[PageableValidator.getFirstResultValue()] Pageable can not be null");
            throw new IllegalArgumentException("[PageableValidator.getFirstResultValue()] Pageable can not be null");
        }
        return (pageable.getPage() - 1) * pageable.getPageSize();
    }
}
