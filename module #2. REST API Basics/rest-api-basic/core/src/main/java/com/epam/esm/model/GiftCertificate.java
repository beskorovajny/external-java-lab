package com.epam.esm.model;


import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode

public class GiftCertificate implements Serializable {
    private static final long serialVersionUID = 7808945757372799936L;

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer duration;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    private Set<Tag> tags = new HashSet<>();
}
