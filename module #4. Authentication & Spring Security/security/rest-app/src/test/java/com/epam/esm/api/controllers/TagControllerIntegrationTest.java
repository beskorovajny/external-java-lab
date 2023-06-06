package com.epam.esm.api.controllers;

import com.epam.esm.api.assembler.TagModelAssembler;
import com.epam.esm.api.errors.RestExceptionHandler;
import com.epam.esm.api.model.TagModel;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.exception.TagAlreadyExistsException;
import com.epam.esm.core.exception.TagNotFoundException;
import com.epam.esm.jpa.configuration.JPAConfig;
import com.epam.esm.jpa.impl.hibernate.TagJPARepository;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.TagServiceImpl;
import com.epam.esm.service.mapping.TagMappingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ActiveProfiles("default")
@ContextConfiguration(classes = {
        JPAConfig.class,
        TagServiceImpl.class,
        TagController.class,
        TagModelAssembler.class,
        TagJPARepository.class,
        TagMappingService.class,
        RestExceptionHandler.class,})
class TagControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @MockBean
    private TagModelAssembler tagModelAssembler;

    @Test
    void should_Save() throws Exception {
        Long id = 7L;
        String name = "tag7";
        TagDTO tagDTO = new TagDTO(id, name);

        given(tagService.save(new TagDTO(null, "tag7"))).willReturn(tagDTO);
        given(tagModelAssembler.toModel(tagDTO)).willReturn(new TagModel(tagDTO));

        this.mockMvc.perform(post("/tags/create")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"tag7\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("tag7"))
                .andExpect(content().contentType("application/hal+json"))
                .andReturn();
    }

    @Test
    void should_Not_Save_ifExists_andThrow() throws Exception {
        given(tagService.save(new TagDTO(null, "java"))).willThrow(TagAlreadyExistsException.class);

        this.mockMvc.perform(post("/tags/create")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"java\"}"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("40005"))
                .andReturn();
    }

    @Test
    void should_Not_Save_andThrow() throws Exception {
        given(tagService.save(new TagDTO(null, null))).willThrow(IllegalArgumentException.class);

        this.mockMvc.perform(post("/tags/create")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"name\": null}"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("40002"))
                .andReturn();
    }

    @Test
    void should_findById() throws Exception {
        Long id = 3L;
        String name = "c";
        TagDTO tagDTO = new TagDTO(id, name);

        given(tagService.findById(anyLong())).willReturn(tagDTO);
        given(tagModelAssembler.toModel(tagDTO)).willReturn(new TagModel(tagDTO));

        this.mockMvc.perform(get("/tags/find/3"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(content().contentType("application/hal+json"))
                .andReturn();
    }

    @Test
    void should_Not_findById_andThrow() throws Exception {
        given(tagService.findById(anyLong())).willThrow(IllegalArgumentException.class);

        this.mockMvc.perform(get("/tags/find/0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("40002"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_findById_andThrow_404() throws Exception {
        given(tagService.findById(anyLong())).willThrow(TagNotFoundException.class);

        this.mockMvc.perform(get("/tags/find/229"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("40404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_findByName() throws Exception {
        Long id = 5L;
        String name = "kotlin";
        TagDTO tagDTO = new TagDTO(id, name);

        given(tagService.findByName(anyString())).willReturn(tagDTO);
        given(tagModelAssembler.toModel(tagDTO)).willReturn(new TagModel(tagDTO));

        this.mockMvc.perform(get("/tags/find?name=kotlin"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(content().contentType("application/hal+json"))
                .andReturn();
    }

    @Test
    void should_Not_findByName_andThrow_IllegalArgumentException() throws Exception {
        given(tagService.findByName(anyString())).willThrow(IllegalArgumentException.class);

        this.mockMvc.perform(get("/tags/find?name="))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("40002"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_findByName_andThrow_TagNotFoundException() throws Exception {
        given(tagService.findByName(anyString())).willThrow(TagNotFoundException.class);

        this.mockMvc.perform(get("/tags/find?name=noNameTag"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("40404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Delete() throws Exception {
        Long id = 7L;
        String name = "c";
        TagDTO tagDTO = new TagDTO(id, name);

        given(tagService.deleteByID(anyLong())).willReturn(tagDTO);
        given(tagModelAssembler.toModel(tagDTO)).willReturn(new TagModel(tagDTO));

        this.mockMvc.perform(delete("/tags/delete/7"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_Delete_andThrow_TagNotFoundException() throws Exception {
        given(tagService.deleteByID(anyLong())).willThrow(TagNotFoundException.class);

        this.mockMvc.perform(delete("/tags/delete/99"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("40404"))
                .andReturn();


    }

    @Test
    void should_Not_Delete_andThrow_IllegalArgumentException() throws Exception {
        given(tagService.deleteByID(anyLong())).willThrow(IllegalArgumentException.class);

        this.mockMvc.perform(delete("/tags/delete/0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("40002"))
                .andReturn();
    }
}