package com.epam.esm.model;


import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode

public class GiftCertificate implements Serializable {
    private static final long serialVersionUID = 7808945757372799936L;

    private Long id;

    @NotBlank
    @NonNull
    private String name;

    @NotBlank
    @NonNull
    private String description;

    @NonNull
    private Double price;

    @NonNull
    private Integer duration;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
