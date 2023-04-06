import com.epam.esm.configuration.initializer.WebAppInitializer;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.repository.configuration.datasource.impl.TestDataSourceConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebAppInitializer.class, TestDataSourceConfig.class})
@WebAppConfiguration()
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class TagIT {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private List<TagDTO> tags;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        tags = Stream.of(new TagDTO(1L, "tag1"), new TagDTO(2L, "tag2"),
                        new TagDTO(3L, "tag3"), new TagDTO(4L, "tag4"),
                        new TagDTO(5L, "tag5"), new TagDTO(6L, "tag6"),
                        new TagDTO(7L, "tag7"))
                .collect(Collectors.toList());
    }

    @Test
    @Order(1)
    void should_Save() throws Exception {
        this.mockMvc.perform(post("/tags/new")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"tag7\"}"))
                .andDo(print()).andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void should_Not_Save_ifExists_andThrow() throws Exception {
        this.mockMvc.perform(post("/tags/new")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"tag5\"}"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage")
                        .value("Tag with given name:[tag5] already exists."))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andReturn();
    }

    @Test
    void should_Not_Save_andThrow() throws Exception {
        this.mockMvc.perform(post("/tags/new")
                        .contentType(MediaType.APPLICATION_JSON).content("null"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void should_findById() throws Exception {
        this.mockMvc.perform(get("/tags/find/3"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tag3"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_findById_andThrow() throws Exception {
        this.mockMvc.perform(get("/tags/find/0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(
                        "An exception occurs: Tag.id can't be less than zero or null"))
                .andExpect(jsonPath("$.errorCode").value("400"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

        this.mockMvc.perform(get("/tags/find/229"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Tag not found (id:[229])"))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_findByName() throws Exception {
        this.mockMvc.perform(get("/tags/find?name=tag5"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tag5"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_findByName_andThrow() throws Exception {
        this.mockMvc.perform(get("/tags/find?name="))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(
                        "The validated character sequence is blank"))
                .andExpect(jsonPath("$.errorCode").value("400"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

        this.mockMvc.perform(get("/tags/find?name=null"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(
                        "Tag not found (name:[null])"))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

        this.mockMvc.perform(get("/tags/find?name=noNameTag"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(
                        "Tag not found (name:[noNameTag])"))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

    }

    @Test
    @Order(2)
    void should_findAll() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/tags/find-all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();

        List<TagDTO> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(tags, actual);
    }

    @Test
    @Order(3)
    void should_Delete() throws Exception {
        this.mockMvc.perform(delete("/tags/delete/7"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void should_Not_Delete_andThrow() throws Exception {
        this.mockMvc.perform(delete("/tags/delete/99"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(
                        "Tag with given id:[99] not found for delete."))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andReturn();

        this.mockMvc.perform(delete("/tags/delete/0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(
                        "Tag.id can't be less than zero."))
                .andExpect(jsonPath("$.errorCode").value("400"))
                .andReturn();
    }


}
