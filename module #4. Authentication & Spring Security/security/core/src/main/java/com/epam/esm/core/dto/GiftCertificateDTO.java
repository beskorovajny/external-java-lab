package com.epam.esm.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftCertificateDTO {

    private Long id;

    @Size(min = 1, max = 45, message = "Gift certificate title length must be between 1 and 45 symbols")
    @NotEmpty(message = "Gift certificate title is mandatory!")
    private String name;

    @Size(min = 1, max = 255, message = "Gift certificate description length must be between 1 and 250 symbols")
    @NotEmpty(message = "Gift certificate description is mandatory!")
    private String description;

    @NotNull(message = "Price is mandatory!")
    private Double price;

    @NotNull(message = "Duration is mandatory")
    private Integer duration;

    @JsonProperty("createDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDate;

    @JsonProperty("lastUpdateDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastUpdateDate;

    @NotNull
    @NotEmpty(message = "Gift certificate requires some tag/s")
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<TagDTO> tags = new HashSet<>();
}
