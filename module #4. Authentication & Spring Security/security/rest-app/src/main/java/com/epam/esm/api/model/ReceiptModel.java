package com.epam.esm.api.model;

import com.epam.esm.core.dto.ReceiptDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptModel extends RepresentationModel<ReceiptModel> {
    @JsonUnwrapped
    private ReceiptDTO receiptDTO;
}
