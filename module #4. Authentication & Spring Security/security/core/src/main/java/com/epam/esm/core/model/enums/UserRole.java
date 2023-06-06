package com.epam.esm.core.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    GUEST(1, "Guest"),
    CUSTOMER(2, "Customer"),
    ADMINISTRATOR(3, "Administrator");

    private final int id;
    private final String roleName;
    }
