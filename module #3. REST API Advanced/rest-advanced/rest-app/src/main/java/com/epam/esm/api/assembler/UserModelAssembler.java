package com.epam.esm.api.assembler;

import com.epam.esm.api.controllers.UserController;
import com.epam.esm.api.model.UserModel;
import com.epam.esm.core.dto.UserDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {
    public static final String FIND_ALL_REL = "findAll";
    public static final String FIND_BY_ID_REL = "findByID";
    public static final String FIND_ALL_BY_NAME_REL = "findAllByName";
    public static final String FIND_RECEIPTS_BY_USER_ID = "findReceiptsByUserID";

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    /**
     * Converts the given entity into a {@code UserModel}, which extends {@link RepresentationModel}.
     *
     * @param entity of UserDTO class which will be converted to representation model
     * @return UserModel object
     */
    @Override
    public UserModel toModel(UserDTO entity) {
        UserModel userModel = new UserModel(entity);

        Link findByIDLink = linkTo(methodOn(UserController.class)
                .findByID(entity.getId()))
                .withRel(FIND_BY_ID_REL);
        Link findAllByName = linkTo(methodOn(UserController.class)
                .findAllByName("John", 1, 10))
                .withRel(FIND_ALL_BY_NAME_REL);
        Link findAllLink = linkTo(methodOn(UserController.class)
                .findAll(1, 10))
                .withRel(FIND_ALL_REL);
        Link findReceiptsByUserID = linkTo(methodOn(UserController.class)
                .findReceiptsByUserID(entity.getId(), 1, 10))
                .withRel(FIND_RECEIPTS_BY_USER_ID);

        userModel.add(findByIDLink, findAllLink, findAllByName, findReceiptsByUserID);

        return userModel;
    }
}
