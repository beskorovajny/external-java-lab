package com.epam.esm.api.assembler;

import com.epam.esm.api.controllers.ReceiptController;
import com.epam.esm.api.model.ReceiptModel;
import com.epam.esm.core.dto.ReceiptDTO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ReceiptModelAssembler extends RepresentationModelAssemblerSupport<ReceiptDTO, ReceiptModel> {
    public ReceiptModelAssembler() {
        super(ReceiptController.class, ReceiptModel.class);
    }

    /**
     * Converts the given entity into a {@code ReceiptModel}, which extends {@link RepresentationModel}.
     *
     * @param entity of ReceiptDTO class  which will be converted to representation model
     * @return ReceiptModel object
     */
    @Override
    public ReceiptModel toModel(ReceiptDTO entity) {
        return null;
    }
}
