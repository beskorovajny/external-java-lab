package com.epam.esm.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode

public class GiftCertificate {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer duration;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
}
