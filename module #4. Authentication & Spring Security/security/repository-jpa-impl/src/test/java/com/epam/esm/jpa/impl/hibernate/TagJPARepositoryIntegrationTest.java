package com.epam.esm.jpa.impl.hibernate;

import com.epam.esm.core.model.entity.Tag;
import com.epam.esm.jpa.configuration.JPAConfig;
import com.epam.esm.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@ContextConfiguration(classes = {JPAConfig.class, TagJPARepository.class})
@ActiveProfiles("default")
class TagJPARepositoryIntegrationTest {
    @Autowired
    private TagRepository tagJPARepository;


    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void isExists() {
        //given
        Tag existedTag = new Tag("c");
        Tag notExistedTag = new Tag("blah-blah");
        //when
        boolean isExistsExpectedTrue = tagJPARepository.isExists(existedTag);
        boolean isExistsExpectedFalse = tagJPARepository.isExists(notExistedTag);
        //then
        then(isExistsExpectedTrue).isTrue();
        then(isExistsExpectedFalse).isFalse();
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void save() {
        //given
        Long generatedID = 1L;
        String name = "test1";
        //when
        Tag savedTag = tagJPARepository.save(new Tag(name));
        //then
        then(savedTag).isNotNull();
        then(savedTag.getId()).isEqualTo(generatedID);
        then(savedTag.getName()).isEqualTo(name);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findByID() {
        //given
        Long id = 4L;
        String expectedName = "c-sharp";
        //when
        Optional<Tag> tagOptional = tagJPARepository.findByID(id);
        Tag tag = null;
        if (tagOptional.isPresent()) tag = tagOptional.get();
        //then
        then(tag).isNotNull();
        then(tag.getId()).isEqualTo(id);
        then(tag.getName()).isEqualTo(expectedName);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findByName() {
        //given
        Long expectedID = 1L;
        String expectedName = "java";
        //when
        Optional<Tag> tagOptional = tagJPARepository.findByName(expectedName);
        Tag tag = null;
        if (tagOptional.isPresent()) tag = tagOptional.get();
        //then
        then(tag).isNotNull();
        then(tag.getId()).isEqualTo(expectedID);
        then(tag.getName()).isEqualTo(expectedName);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findAll() {
        //given
        Pageable pageableDefault = PageRequest.of(0, 10);
        Pageable pageableCustom = PageRequest.of(0, 4);
        //when
        List<Tag> defaultList = tagJPARepository.findAll(pageableDefault);
        List<Tag> customList = tagJPARepository.findAll(pageableCustom);
        //then
        then(defaultList.size()).isEqualTo(6);
        then(customList.size()).isEqualTo(4);
        then(defaultList).isEqualTo(getAll());
        then(customList).isEqualTo(getAllWithPageParams());
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findAllByCertificate() {
        //given
        Long certificateID = 4L;
        Pageable pageable = PageRequest.of(0, 10);
        //when
        List<Tag> tagsByCertificateID = tagJPARepository.findAllByCertificate(certificateID, pageable);
        //then
        then(tagsByCertificateID.size()).isEqualTo(1);
        then(tagsByCertificateID).isEqualTo(getAllForCertificateIDEqualTo4());
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts() {
        //given
        Tag expected = new Tag(4L, "c-sharp", new HashSet<>());
        Tag tag = null;
        //when
        Optional<Tag> result = tagJPARepository.findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();
        if (result.isPresent()) tag = result.get();
        //then
        then(tag).isNotNull();
        then(tag).isEqualTo(expected);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void deleteByID() {
        //given
        Long id = 5L;
        String expectedName = "kotlin";
        //when
        Tag removedTag = tagJPARepository.deleteByID(id);
        //then
        then(removedTag).isNotNull();
        then(removedTag.getId()).isEqualTo(id);
        then(removedTag.getName()).isEqualTo(expectedName);
        assertThatThrownBy(() -> tagJPARepository.deleteByID(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("attempt to create delete event with null entity");

    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void getTotalRecords() {
        //given
        Long expectedRecords = 6L;
        //when
        Long actual = tagJPARepository.getTotalRecords();
        //then
        then(actual).isEqualTo(expectedRecords);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void getTotalRecordsForGiftCertificateID() {
        //given
        Long certificateID = 3L;
        Long expectedRecords = 2L;
        //when
        Long actualRecords = tagJPARepository.getTotalRecordsForGiftCertificateID(certificateID);
        //then
        then(actualRecords).isEqualTo(expectedRecords);
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

    private List<Tag> getAllForCertificateIDEqualTo4() {
        return List.of(
                new Tag(5L, "kotlin", new HashSet<>()));
    }
}