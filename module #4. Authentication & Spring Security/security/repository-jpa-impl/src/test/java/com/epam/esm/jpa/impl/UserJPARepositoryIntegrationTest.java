/*
package integration.jpa.hibernate;

import com.epam.esm.jpa.config.JPAConfig;
import com.epam.esm.core.model.entity.User;
import com.epam.esm.core.model.enums.UserRole;
import com.epam.esm.jpa.impl.hibernate.UserJPARepository;
import com.epam.esm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
@ContextConfiguration(classes = {
        JPAConfig.class,
        UserJPARepository.class})
@ActiveProfiles("default")
class UserJPARepositoryIntegrationTest {

    @Autowired
    private UserRepository userJPARepository;

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
        String email = "rtofftspj@intel.com";
        String firstName = "Rozetta";
        String lastName = "Stone";
        String encodedPassword = "$2a$10$CJSDqMFXSIpJ48bvn6h44.qnk/FsUl2IYBsuiwtdzdAvJCbJhERaW";
        User forSave = User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(encodedPassword)
                .userRole(UserRole.CUSTOMER)
                .build();
        //when
        User savedUser = userJPARepository.save(forSave);

        //then
        then(savedUser).isNotNull();
        then(savedUser.getId()).isEqualTo(generatedID);
        then(savedUser.getEmail()).isEqualTo(email);
        then(savedUser.getFirstName()).isEqualTo(firstName);
        then(savedUser.getLastName()).isEqualTo(lastName);
        then(savedUser.getPassword()).isEqualTo(encodedPassword);
        then(savedUser.getUserRole()).isEqualTo(UserRole.CUSTOMER);
    }

    @Sql(scripts = {"/schema-h2.sql", "/data-h2.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void findByID() {
        //given
        Long id = 3L;
        String expectedEmail = "lblatchford2@rambler.ru";
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
        String expectedEmail = "btooher3@wikispaces.com";
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
        String partOfName = "Be";
        Pageable pageable = PageRequest.of(0, 5);
        //when
        List<User> actual = userJPARepository.findAllByName(partOfName, pageable);
        //then
        then(actual.size()).isEqualTo(4);
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
        String expectedEmail = "ebutchard0@ox.ac.uk";
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
        Long expectedRecords = 50L;
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
        Long expectedRecords = 4L;
        String partOfName = "Be";
        //when
        Long actual = userJPARepository.getTotalRecordsForNameLike(partOfName);
        //then
        then(actual).isEqualTo(expectedRecords);
    }

    private List<User> getAllWithPageParams() {
        return List.of(
                User.builder()
                        .id(6L)
                        .email("vhalbord5@washingtonpost.com")
                        .firstName("Vale")
                        .lastName("Halbord")
                        .password("3Dx9KZp")
                        .userRole(UserRole.CUSTOMER)
                        .build(),
                User.builder()
                        .id(7L)
                        .email("ttodhunter6@macromedia.com")
                        .firstName("Theodoric")
                        .lastName("Todhunter")
                        .password("BiZIzGokPq")
                        .userRole(UserRole.CUSTOMER)
                        .build(),
                User.builder()
                        .id(8L)
                        .email("lianiello7@tripod.com")
                        .firstName("Lenci")
                        .lastName("Ianiello")
                        .password("w9qdwSvFb2So")
                        .userRole(UserRole.CUSTOMER)
                        .build(),
                User.builder()
                        .id(9L)
                        .email("apinwill8@census.gov")
                        .firstName("Algernon")
                        .lastName("Pinwill")
                        .password("pu1iNg")
                        .userRole(UserRole.CUSTOMER)
                        .build(),
                User.builder()
                        .id(10L)
                        .email("bmyrick9@wp.com")
                        .firstName("Beck")
                        .lastName("Myrick")
                        .password("di7uJL4Fhuwa")
                        .userRole(UserRole.CUSTOMER)
                        .build()
        );
    }

    private List<User> getAllForNameLike() {
        return List.of(
                User.builder()
                        .id(4L)
                        .email("btooher3@wikispaces.com")
                        .firstName("Bent")
                        .lastName("Tooher")
                        .password("4SEgIXZKaXOZ")
                        .userRole(UserRole.CUSTOMER)
                        .build(),
                User.builder()
                        .id(10L)
                        .email("bmyrick9@wp.com")
                        .firstName("Beck")
                        .lastName("Myrick")
                        .password("di7uJL4Fhuwa")
                        .userRole(UserRole.CUSTOMER)
                        .build(),
                User.builder()
                        .id(11L)
                        .email("gvaggesa@upenn.edu")
                        .firstName("Giselbert")
                        .lastName("Vagges")
                        .password("xYNvdz7CFvE")
                        .userRole(UserRole.CUSTOMER)
                        .build(),
                User.builder()
                        .id(30L)
                        .email("mstaniont@cloudflare.com")
                        .firstName("Maybelle")
                        .lastName("Stanion")
                        .password("jI3oGR0vKvD")
                        .userRole(UserRole.CUSTOMER)
                        .build()
        );
    }
}
*/
