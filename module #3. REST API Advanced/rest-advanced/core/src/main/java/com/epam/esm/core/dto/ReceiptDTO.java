package com.epam.esm.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
    private Long id;
    private Double price;
    @JsonProperty("createDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDate;
    @JsonProperty("user")
    private UserDTO userDTO;
    @EqualsAndHashCode.Exclude
    private Set<GiftCertificateDTO> giftCertificates = new HashSet<>();
}
