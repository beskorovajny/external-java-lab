package com.epam.esm.api.controllers;

import com.epam.esm.api.assembler.ReceiptModelAssembler;
import com.epam.esm.api.assembler.UserModelAssembler;
import com.epam.esm.api.errors.RestExceptionHandler;
import com.epam.esm.api.model.UserModel;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.exception.UserNotFoundException;
import com.epam.esm.jpa.configuration.JPAConfig;
import com.epam.esm.jpa.impl.hibernate.GiftCertificateJPARepository;
import com.epam.esm.jpa.impl.hibernate.ReceiptJPARepository;
import com.epam.esm.jpa.impl.hibernate.TagJPARepository;
import com.epam.esm.jpa.impl.hibernate.UserJPARepository;
import com.epam.esm.jpa.utils.QueryProvider;
import com.epam.esm.service.UserService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.service.impl.ReceiptServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import com.epam.esm.service.impl.UserServiceImpl;
import com.epam.esm.service.mapping.GiftCertificateMappingService;
import com.epam.esm.service.mapping.ReceiptMappingService;
import com.epam.esm.service.mapping.TagMappingService;
import com.epam.esm.service.mapping.UserMappingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ActiveProfiles("default")
@ContextConfiguration(classes = {
        JPAConfig.class,
        UserServiceImpl.class,
        UserController.class,
        UserModelAssembler.class,
        UserJPARepository.class,
        UserMappingService.class,
        RestExceptionHandler.class,
        ReceiptServiceImpl.class,
        ReceiptJPARepository.class,
        ReceiptMappingService.class,
        ReceiptModelAssembler.class,
        GiftCertificateServiceImpl.class,
        GiftCertificateJPARepository.class,
        GiftCertificateMappingService.class,
        TagServiceImpl.class,
        TagJPARepository.class,
        TagMappingService.class,
        QueryProvider.class})
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserModelAssembler userModelAssembler;

    @Test
    void should_findById() throws Exception {
        Long id = 3L;
        String email = "someMail@mail.com";
        String name = "f_name";
        String lastName = "l_name";
        UserDTO userDTO = UserDTO.builder()
                .id(id)
                .email(email)
                .firstName(name)
                .lastName(lastName)
                .build();

        given(userService.findById(anyLong())).willReturn(userDTO);
        given(userModelAssembler.toModel(userDTO)).willReturn(new UserModel(userDTO));

        this.mockMvc.perform(get("/users/find/3"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value(name))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(content().contentType("application/hal+json"))
                .andReturn();
    }

    @Test
    void should_Not_findById_andThrow() throws Exception {
        given(userService.findById(anyLong())).willThrow(IllegalArgumentException.class);

        this.mockMvc.perform(get("/users/find/0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("40002"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_findById_andThrow_404() throws Exception {
        given(userService.findById(anyLong())).willThrow(UserNotFoundException.class);

        this.mockMvc.perform(get("/users/find/229"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("40409"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }
}