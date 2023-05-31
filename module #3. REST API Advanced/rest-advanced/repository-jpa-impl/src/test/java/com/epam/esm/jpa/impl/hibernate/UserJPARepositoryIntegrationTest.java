package com.epam.esm.jpa.impl.hibernate;

import com.epam.esm.core.model.entity.User;
import com.epam.esm.jpa.configuration.JPAConfig;
import com.epam.esm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@ContextConfiguration(classes = {JPAConfig.class, UserJPARepository.class})
@ActiveProfiles("default")
class UserJPARepositoryIntegrationTest {

    @Autowired
    private UserRepository userJPARepository;

    @Autowired
    private TestEntityManager entityManager;

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void isExists() {
        //given
        String existedEmail = "rtofftspj@intel.com";
        String existedFirstName = "Rozella";
        String existedLastName = "Toffts";
        User existedUser = User.builder()
                .id(4L)
                .email(existedEmail)
                .firstName(existedFirstName)
                .lastName(existedLastName)
                .build();
        User notExistedUser = User.builder().id(500L).email("user@mail.com").build();
        //when
        boolean isExistsExpectedTrue = userJPARepository.isExists(existedUser);
        boolean isExistsExpectedFalse = userJPARepository.isExists(notExistedUser);
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
        String existedEmail = "rtofftspj@intel.com";
        String existedFirstName = "Rozetta";
        String existedLastName = "Stone";
        User forSave = User.builder()
                .email(existedEmail)
                .firstName(existedFirstName)
                .lastName(existedLastName)
                .build();
        //when
        User savedUser = entityManager.persistAndFlush(forSave);

        Optional<User> receivedUserOpt = userJPARepository.findByID(generatedID);
        User receivedUser = null;
        if (receivedUserOpt.isPresent()) receivedUser = receivedUserOpt.get();

        //then
        then(receivedUser).isNotNull();
        then(receivedUser.getId()).isEqualTo(generatedID);
        then(receivedUser.getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findByID() {
        //given
        Long id = 3L;
        String expectedEmail = "fspellardpi@oracle.com";
        //when
        Optional<User> userOptional = userJPARepository.findByID(id);
        User user = null;
        if (userOptional.isPresent()) user = userOptional.get();
        //then
        then(user).isNotNull();
        then(user.getId()).isEqualTo(id);
        then(user.getEmail()).isEqualTo(expectedEmail);
    }


    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findByReceipt() {
        //given
        Long receiptID = 5L;
        Long expectedUserID = 4L;
        String expectedEmail = "rtofftspj@intel.com";
        //when
        Optional<User> userOptional = userJPARepository.findByReceipt(receiptID);
        User user = null;
        if (userOptional.isPresent()) user = userOptional.get();
        //then
        then(user).isNotNull();
        then(user.getId()).isEqualTo(expectedUserID);
        then(user.getEmail()).isEqualTo(expectedEmail);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findAllByName() {
        //given
        String partOfName = "Je";
        Pageable pageable = PageRequest.of(0, 5);
        //when
        List<User> actual = userJPARepository.findAllByName(partOfName, pageable);
        //then
        then(actual.size()).isEqualTo(2);
        then(actual).isEqualTo(getAllForNameLike());
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findAll() {
        //given
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        //when
        List<User> defaultList = userJPARepository.findAll(pageable);
        //then
        then(defaultList.size()).isEqualTo(pageSize);
        then(defaultList).isEqualTo(getAllWithPageParams());
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void deleteByID() {
        //given
        Long id = 1L;
        String expectedEmail = "mrawstronpg@homestead.com";
        //when
        User removedUser = userJPARepository.deleteByID(id);
        //then
        then(removedUser).isNotNull();
        then(removedUser.getId()).isEqualTo(id);
        then(removedUser.getEmail()).isEqualTo(expectedEmail);
        assertThatThrownBy(() -> userJPARepository.deleteByID(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("attempt to create delete event with null entity");
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void getTotalRecords() {
        //given
        Long expectedRecords = 84L;
        //when
        Long actual = userJPARepository.getTotalRecords();
        //then
        then(actual).isEqualTo(expectedRecords);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void getTotalRecordsForNameLike() {
        //given
        Long expectedRecords = 2L;
        String partOfName = "je";
        //when
        Long actual = userJPARepository.getTotalRecordsForNameLike(partOfName);
        //then
        then(actual).isEqualTo(expectedRecords);
    }

    private List<User> getAllWithPageParams() {
        return List.of(
                User.builder()
                        .id(6L)
                        .email("rgoddmanpl@google.it")
                        .firstName("Rance")
                        .lastName("Goddman")
                        .build(),
                User.builder()
                        .id(7L)
                        .email("kfarakerpm@bbc.co.uk")
                        .firstName("Kettie")
                        .lastName("Faraker")
                        .build(),
                User.builder()
                        .id(8L)
                        .email("achesselpn@lycos.com")
                        .firstName("Antoine")
                        .lastName("Chessel")
                        .build(),
                User.builder()
                        .id(9L)
                        .email("fdorkinspo@ocn.ne.jp")
                        .firstName("Frannie")
                        .lastName("Dorkins")
                        .build(),
                User.builder()
                        .id(10L)
                        .email("jgiblettpp@gnu.org")
                        .firstName("Jeno")
                        .lastName("Giblett")
                        .build()
                );
    }
    private List<User> getAllForNameLike() {
        return List.of(
                User.builder()
                        .id(10L)
                        .email("jgiblettpp@gnu.org")
                        .firstName("Jeno")
                        .lastName("Giblett")
                        .build(),
                User.builder()
                        .id(72L)
                        .email("jshackellrf@slashdot.org")
                        .firstName("Jeremiah")
                        .lastName("Shackell")
                        .build()
        );
    }
}