import com.epam.esm.configuration.initializer.WebAppInitializer;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.repository.configuration.datasource.impl.TestDataSourceConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebAppInitializer.class, TestDataSourceConfig.class})
@WebAppConfiguration()
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateIT {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private List<GiftCertificateDTO> certificates;

    private static final String certificateJSON = "{\n" +
            "        \"name\": \"gc1\",\n" +
            "        \"description\": \"des1 new\",\n" +
            "        \"price\": 99.0,\n" +
            "        \"duration\": 16\n" +
            "    }";

    private static final String certificateJSONExists = "{\n" +
            "        \"name\": \"certificate1\",\n" +
            "        \"description\": \"des1 new\",\n" +
            "        \"price\": 99.0,\n" +
            "        \"duration\": 16\n" +
            "    }";

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        certificates = getCertificates().collect(Collectors.toList());
    }

    @Test
    @Order(1)
    void should_Save() throws Exception {
        this.mockMvc.perform(post("/certificates/new")
                        .contentType(MediaType.APPLICATION_JSON).content(certificateJSON))
                .andDo(print()).andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void should_Not_Save_ifExists_andThrow() throws Exception {
        this.mockMvc.perform(post("/certificates/new")
                        .contentType(MediaType.APPLICATION_JSON).content(certificateJSONExists))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage")
                        .value("GiftCertificate with given name:[certificate1] already exists."))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andReturn();
    }

    @Test
    void should_Not_Save_andThrow() throws Exception {
        this.mockMvc.perform(post("/certificates/new")
                        .contentType(MediaType.APPLICATION_JSON).content("null"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void should_findById() throws Exception {
        this.mockMvc.perform(get("/certificates/find/5"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("certificate5"))
                .andExpect(jsonPath("$.description").value("desc5"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_findById_andThrow() throws Exception {
        this.mockMvc.perform(get("/certificates/find/0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(
                        "GiftCertificate.id can't be less than zero or null"))
                .andExpect(jsonPath("$.errorCode").value("400"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

        this.mockMvc.perform(get("/certificates/find/229"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("GiftCertificate not found (id:[229])"))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_findByName() throws Exception {
        this.mockMvc.perform(get("/certificates/find?name=certificate2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("certificate2"))
                .andExpect(jsonPath("$.description").value("desc2"))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    void should_Not_findByName_andThrow() throws Exception {
        this.mockMvc.perform(get("/certificates/find?name="))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(
                        "The validated character sequence is blank"))
                .andExpect(jsonPath("$.errorCode").value("400"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

        this.mockMvc.perform(get("/certificates/find?name=null"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(
                        "GiftCertificate not found (name:[null])"))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

        this.mockMvc.perform(get("/certificates/find?name=noNameCert"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(
                        "GiftCertificate not found (name:[noNameCert])"))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andExpect(content().contentType("application/json"))
                .andReturn();

    }

    @Test
    void should_findAll() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/certificates/find-all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        List<GiftCertificateDTO> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(certificates, actual);
    }

    @Test
    @Order(2)
    void should_Delete() throws Exception {
        this.mockMvc.perform(delete("/certificates/delete/7"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void should_Not_Delete_andThrow() throws Exception {
        this.mockMvc.perform(delete("/certificates/delete/99"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(
                        "Certificate with given id:[99] not found for delete."))
                .andExpect(jsonPath("$.errorCode").value("404"))
                .andReturn();

        this.mockMvc.perform(delete("/certificates/delete/0"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(
                        "GiftCertificate.id can't be less than zero or null"))
                .andExpect(jsonPath("$.errorCode").value("400"))
                .andReturn();
    }


    private static Stream<GiftCertificateDTO> getCertificates() {
        return Stream.of(GiftCertificateDTO.builder()
                        .id(1L)
                        .name("certificate1")
                        .description("desc1")
                        .price(55.0)
                        .duration(2)
                        .createDate(LocalDateTime.of(2023, 3, 23,
                                        15, 58, 5, 284000000))
                        .build(),
                GiftCertificateDTO.builder()
                        .id(2L)
                        .name("certificate2")
                        .description("desc2")
                        .price(55.0)
                        .duration(2)
                        .createDate(LocalDateTime.of(2023, 3, 23,
                                        15, 59, 5, 284000000))
                        .build(),
                GiftCertificateDTO.builder()
                        .id(3L)
                        .name("certificate3")
                        .description("desc3")
                        .price(55.0)
                        .duration(2)
                        .createDate(LocalDateTime.of(2023, 3, 23,
                                        16, 0, 5, 284000000))
                        .build(),
                GiftCertificateDTO.builder()
                        .id(4L)
                        .name("certificate4")
                        .description("desc4")
                        .price(55.0)
                        .duration(2)
                        .createDate(LocalDateTime.of(2023, 3, 23,
                                        17, 58, 5, 284000000))
                        .build(),
                GiftCertificateDTO.builder()
                        .id(5L)
                        .name("certificate5")
                        .description("desc5")
                        .price(55.0)
                        .duration(2)
                        .createDate(LocalDateTime.of(2023, 3, 23,
                                        18, 58, 5, 284000000))
                        .build(),
                GiftCertificateDTO.builder()
                        .id(6L)
                        .name("certificate6")
                        .description("desc6")
                        .price(55.0)
                        .duration(2)
                        .createDate(LocalDateTime.of(2023, 3, 23,
                                        19, 58, 5, 284000000))
                        .build());
    }
}
