package com.epam.esm.core.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptRequestBody {

    @NotNull
    @NotEmpty(message = "You should choose some gift certificate/s to make an order!")
    private Set<Long> giftCertificatesIDs = new HashSet<>();
}
