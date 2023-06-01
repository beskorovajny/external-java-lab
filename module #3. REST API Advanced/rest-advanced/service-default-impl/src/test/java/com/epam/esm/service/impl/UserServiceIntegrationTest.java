package com.epam.esm.service.impl;

import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.exception.UserNotFoundException;
import com.epam.esm.core.model.entity.User;
import com.epam.esm.jpa.configuration.JPAConfig;
import com.epam.esm.jpa.impl.hibernate.UserJPARepository;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.MappingService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.mapping.UserMappingService;
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

import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ContextConfiguration(classes = {
        JPAConfig.class,
        UserJPARepository.class,
        UserServiceImpl.class,
        UserMappingService.class})
@Transactional
@ActiveProfiles("default")
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MappingService<User, UserDTO> mappingService;

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findById() {
        //given
        Long id = 3L;
        String expectedEmail = "fspellardpi@oracle.com";
        //when
        User result = mappingService.mapFromDto(userService.findById(id));
        //then
        then(result).isNotNull();
        then(result.getId()).isEqualTo(id);
        then(result.getEmail()).isEqualTo(expectedEmail);
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void should_Not_Find_By_ID() {
        //given
        Long generatedID = 20L;
        //when
        Throwable throwable = catchThrowable(() -> userService.findById(generatedID));
        //then
        then(throwable)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User not found (id:");
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findAllByName() {
        //given
        String partOfName = "Je";
        Pageable pageable = PageRequest.of(0, 5);
        //when
        List<User> actual = userService.findAllByName(partOfName, pageable)
                .stream()
                .map(mappingService::mapFromDto)
                .toList();
        //then
        then(actual.size()).isEqualTo(2);
        then(actual).isEqualTo(getAllForNameLike());
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
        User result = mappingService.mapFromDto(userService.findByReceipt(receiptID));
        //then
        then(result).isNotNull();
        then(result.getId()).isEqualTo(expectedUserID);
        then(result.getEmail()).isEqualTo(expectedEmail);
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
        List<User> defaultList = userService.findAll(pageable)
                .stream()
                .map(mappingService::mapFromDto)
                .toList();
        //then
        then(defaultList.size()).isEqualTo(pageSize);
        then(defaultList).isEqualTo(getAllWithPageParams());
    }

    @Sql(scripts = "/schema-h2.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void should_Not_Find_All_If_Empty_DB() {
        //given
        Pageable pageableDefault = PageRequest.of(0, 10);
        //when
        Throwable throwable = catchThrowable(() -> userService.findAll(pageableDefault));
        //then
        then(throwable)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("Users not found");
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
}