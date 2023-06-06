package com.epam.esm.core.payload.request;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class AuthRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
