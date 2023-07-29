package com.epam.esm.api.model;

import com.epam.esm.core.dto.GiftCertificateDTO;
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
public class GiftCertificateModel extends RepresentationModel<GiftCertificateModel> {
    @JsonUnwrapped
    private GiftCertificateDTO giftCertificateDTO;
}
