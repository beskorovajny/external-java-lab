package com.epam.esm.core.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptRequestBody {
    private Long userID;
    private Set<Long> giftCertificatesIDs;
}
