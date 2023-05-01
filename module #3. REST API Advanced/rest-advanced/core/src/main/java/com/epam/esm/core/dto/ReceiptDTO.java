package com.epam.esm.core.dto;

import com.epam.esm.core.model.GiftCertificate;
import com.epam.esm.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
    private Long id;
    private Double price;
    private LocalDateTime createDate;
    private User user;
    private Set<GiftCertificate> certificates;
}
