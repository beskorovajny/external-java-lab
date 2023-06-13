package com.epam.esm.core.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    CUSTOMER(1, "Customer"),
    ADMIN(2, "Admin");

    private final int id;
    private final String roleName;
    }
