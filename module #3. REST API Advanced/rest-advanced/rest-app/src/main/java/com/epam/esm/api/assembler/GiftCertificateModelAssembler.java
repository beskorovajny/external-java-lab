package com.epam.esm.api.assembler;

import com.epam.esm.api.controllers.GiftCertificateController;
import com.epam.esm.api.model.GiftCertificateModel;
import com.epam.esm.api.model.ReceiptModel;
import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.model.entity.GiftCertificate;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.ui.Model;

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
        return null;
    }
}
