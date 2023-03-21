package com.epam.esm.model;

import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Tag implements Serializable {
    private static final long serialVersionUID = 7211928784526853247L;

    private Long id;

    @NotBlank
    @NonNull
    private String name;
}
