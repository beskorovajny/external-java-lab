package com.epam.esm.service.impl;

import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.exception.TagAlreadyExistsException;
import com.epam.esm.core.exception.TagNotFoundException;
import com.epam.esm.core.model.entity.Tag;
import com.epam.esm.jpa.configuration.JPAConfig;
import com.epam.esm.jpa.impl.hibernate.TagJPARepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.MappingService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.mapping.TagMappingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ContextConfiguration(classes = {
        JPAConfig.class,
        TagJPARepository.class,
        TagServiceImpl.class,
        TagMappingService.class})
@Transactional
@ActiveProfiles("default")
class TagServiceIntegrationTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    MappingService<Tag, TagDTO> mappingService;

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void save() {
        //given
        Long generatedID = 1L;
        String name = "test1";
        //when
        Tag savedTag = mappingService.mapFromDto(tagService.save(new TagDTO(null, name)));
        //then
        then(savedTag).isNotNull();
        then(savedTag.getId()).isEqualTo(generatedID);
        then(savedTag.getName()).isEqualTo(name);
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void should_Not_Save_If_Exists() {
        //given
        String name = "test1";
        Tag tag = new Tag( name);
        tagRepository.save(tag);
        //when
        Throwable throwable = catchThrowable(() ->tagService.save(new TagDTO(null, name)));
        //then
        then(throwable)
                .isInstanceOf(TagAlreadyExistsException.class)
                .hasMessageContaining(" already exists.");
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findById() {
        //given
        Long generatedID = 1L;
        Tag newTag = new Tag("tagName");
        Tag saved = tagRepository.save(newTag);
        //when
        Tag result = mappingService.mapFromDto(tagService.findById(generatedID));
        //then
        then(result).isNotNull();
        then(result).isEqualTo(saved);
    }
    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void should_Not_Find_By_ID() {
        //given
        Long generatedID = 20L;
        //when
        Throwable throwable = catchThrowable(() -> tagService.findById(generatedID));
        //then
        then(throwable)
                .isInstanceOf(TagNotFoundException.class)
                .hasMessageContaining("Tag not found (id:");
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findByName() {
        //given
        String name = "some_name";
        Tag newTag = new Tag(name);
        Tag saved = tagRepository.save(newTag);
        //when
        Tag result = mappingService.mapFromDto(tagService.findByName(name));
        //then
        then(result).isNotNull();
        then(result).isEqualTo(saved);
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void should_Not_Find_By_Name() {
        //given
        String name = "some_name";
        //when
        Throwable throwable = catchThrowable(() -> tagService.findByName(name));
        //then
        then(throwable)
                .isInstanceOf(TagNotFoundException.class)
                .hasMessageContaining("Tag not found (name:");
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findAllByCertificate() {
        //given
        Long certificateID = 4L;
        Pageable pageable = PageRequest.of(0, 10);
        //when
        List<Tag> tagsByCertificateID = tagService.findAllByCertificate(certificateID, pageable)
                .stream()
                .map(mappingService::mapFromDto)
                .toList();
        //then
        then(tagsByCertificateID.size()).isEqualTo(2);
        then(tagsByCertificateID).isEqualTo(getAllForCertificateIDEqualTo4());
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts() {
        //given
        Tag expected = new Tag(1L, "java", new HashSet<>());
        //when
        Tag result = mappingService.mapFromDto(tagService.findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts());
        //then
        then(result).isNotNull();
        then(result).isEqualTo(expected);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findAll() {
        //given
        Pageable pageableDefault = PageRequest.of(0, 10);
        Pageable pageableCustom = PageRequest.of(0, 4);
        //when
        List<Tag> defaultList = tagService.findAll(pageableDefault)
                .stream()
                .map(mappingService::mapFromDto)
                .toList();
        List<Tag> customList = tagService.findAll(pageableCustom)
                .stream()
                .map(mappingService::mapFromDto)
                .toList();
        //then
        then(defaultList.size()).isEqualTo(6);
        then(customList.size()).isEqualTo(4);
        then(defaultList).isEqualTo(getAll());
        then(customList).isEqualTo(getAllWithPageParams());
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void should_Not_Find_All_If_Empty_DB() {
        //given
        Pageable pageableDefault = PageRequest.of(0, 10);
        //when
        Throwable throwable = catchThrowable(() -> tagService.findAll(pageableDefault));
        //then
        then(throwable)
                .isInstanceOf(TagNotFoundException.class)
                .hasMessageContaining("Tags not found");
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void deleteById() {
        //given
        Long id = 5L;
        String expectedName = "kotlin";
        //when
        Tag removedTag = mappingService.mapFromDto(tagService.deleteByID(id));
        //then
        then(removedTag).isNotNull();
        then(removedTag.getId()).isEqualTo(id);
        then(removedTag.getName()).isEqualTo(expectedName);
        assertThatThrownBy(() -> tagService.deleteByID(id))
                .isInstanceOf(TagNotFoundException.class)
                .hasMessageContaining("not found for delete.");
    }

    private List<Tag> getAllForCertificateIDEqualTo4() {
        return List.of(
                new Tag(1L, "java", new HashSet<>()),
                new Tag(5L, "kotlin", new HashSet<>()));
    }

    private List<Tag> getAll() {
        return List.of(
                new Tag(1L, "java", new HashSet<>()),
                new Tag(2L, "scala", new HashSet<>()),
                new Tag(3L, "c", new HashSet<>()),
                new Tag(4L, "c-sharp", new HashSet<>()),
                new Tag(5L, "kotlin", new HashSet<>()),
                new Tag(6L, "visual basic", new HashSet<>()));
    }

    private List<Tag> getAllWithPageParams() {
        return List.of(
                new Tag(1L, "java", new HashSet<>()),
                new Tag(2L, "scala", new HashSet<>()),
                new Tag(3L, "c", new HashSet<>()),
                new Tag(4L, "c-sharp", new HashSet<>()));
    }
}