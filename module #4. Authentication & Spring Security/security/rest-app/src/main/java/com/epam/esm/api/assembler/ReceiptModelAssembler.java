package com.epam.esm.api.assembler;

import com.epam.esm.api.controllers.ReceiptController;
import com.epam.esm.api.model.ReceiptModel;
import com.epam.esm.core.dto.ReceiptDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReceiptModelAssembler extends RepresentationModelAssemblerSupport<ReceiptDTO, ReceiptModel> {

    public static final String DELETE_REL = "delete";
    public static final String USER_REL = "user";
    public static final String CERTIFICATES_REL = "gift certificates";
    private static final int PAGE = 0;
    private static final int SIZE = 10;

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
    public @NotNull ReceiptModel toModel(@NotNull ReceiptDTO entity) {
        ReceiptModel receiptModel = new ReceiptModel(entity);

        receiptModel.add(
                linkTo(
                        methodOn(ReceiptController.class)
                                .findByID(entity.getId()))
                        .withSelfRel(),
                linkTo(
                        methodOn(ReceiptController.class)
                                .findUserByReceipt(entity.getId()))
                        .withRel(USER_REL),
                linkTo(
                        methodOn(ReceiptController.class)
                                .findGiftCertificatesByReceipt(entity.getId(), PageRequest.of(PAGE, SIZE)))
                        .withRel(CERTIFICATES_REL),
                linkTo(
                        methodOn(ReceiptController.class)
                                .deleteByID(entity.getId()))
                        .withRel(DELETE_REL));

        return receiptModel;
    }
}
