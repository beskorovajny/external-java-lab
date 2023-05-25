package com.epam.esm.api.assembler;

import com.epam.esm.api.controllers.GiftCertificateController;
import com.epam.esm.api.controllers.ReceiptController;
import com.epam.esm.api.model.GiftCertificateModel;
import com.epam.esm.core.dto.GiftCertificateDTO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateModelAssembler extends
        RepresentationModelAssemblerSupport<GiftCertificateDTO, GiftCertificateModel> {
    public GiftCertificateModelAssembler() {
        super(GiftCertificateController.class, GiftCertificateModel.class);
    }

    /**
     * Converts the given entity into a {@code GiftCertificateModel}, which extends {@link RepresentationModel}.
     *
     * @param entity of GiftCertificateDTO class  which will be converted to representation model
     * @return GiftCertificateModel object
     */
    @Override
    public GiftCertificateModel toModel(GiftCertificateDTO entity) {
        GiftCertificateModel certificateModel = new GiftCertificateModel(entity);

        certificateModel.add(linkTo(
                methodOn(ReceiptController.class)
                        .findByID(entity.getId()))
                .withSelfRel());

        return certificateModel;
    }
}
