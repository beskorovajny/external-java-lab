package com.epam.esm.api.assembler;

import com.epam.esm.api.controllers.UserController;
import com.epam.esm.api.model.UserModel;
import com.epam.esm.core.dto.UserDTO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {
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

        userModel.add(linkTo(
                methodOn(UserController.class)
                        .findByID(entity.getId()))
                .withSelfRel());

        return userModel;
    }
}
