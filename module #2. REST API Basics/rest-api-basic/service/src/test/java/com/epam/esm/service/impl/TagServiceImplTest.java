package com.epam.esm.service.impl;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.exception.model.TagAlreadyExistsException;
import com.epam.esm.exception.model.TagNotFoundException;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.impl.jdbctemplate.mysql.TagJDBCTemplate;
import com.epam.esm.service.mapping.impl.TagMappingServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    TagJDBCTemplate tagJDBCTemplate;
    @Mock
    TagMappingServiceImpl mappingService;
    @InjectMocks
    TagServiceImpl tagService;
    TagDTO tagDTO;
    Tag expectedTag;
    long id;
    String name;


    @BeforeEach
    void setUp() {
        id = 1L;
        name = "testTag";
        tagDTO = new TagDTO(id, name);
        expectedTag = new Tag(id, name);
    }

    @AfterEach
    void tearDown() {
        tagDTO = null;
    }

    @Test
    void should_Save() {
        when(tagJDBCTemplate.findByName(tagDTO.getName())).thenReturn(Optional.empty());
        when(mappingService.mapFromDto(tagDTO)).thenReturn(expectedTag);
        when(tagJDBCTemplate.save(expectedTag)).thenReturn(id);

        tagService.save(tagDTO);

        verify(tagJDBCTemplate, times(1)).save(expectedTag);
    }

    @Test
    void should_Not_Save_If_Exists() {
        when(tagJDBCTemplate.findByName(tagDTO.getName())).thenReturn(Optional.of(expectedTag));
        assertThrows(TagAlreadyExistsException.class, () -> tagService.save(tagDTO));
    }

    @Test
    void should_FindById() {
        when(tagJDBCTemplate.findById(id)).thenReturn(Optional.of(expectedTag));
        when(mappingService.mapToDto(expectedTag)).thenReturn(tagDTO);

        TagDTO tagDTOExpected = tagService.findById(id);

        assertEquals(tagDTO, tagDTOExpected);
    }

    @Test
    void should_Not_FindById_If_Not_Exists_And_Throw() {
        when(tagJDBCTemplate.findById(id)).thenReturn(Optional.empty());
        assertThrows(TagNotFoundException.class, () -> tagService.findById(id));
    }

    @Test
    void should_Not_FindById_And_Throw_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> tagService.findById(0L));
        assertThrows(IllegalArgumentException.class, () -> tagService.findById(null));
    }


    @Test
    void should_FindByName() {
        when(tagJDBCTemplate.findByName(name)).thenReturn(Optional.of(expectedTag));
        when(mappingService.mapToDto(expectedTag)).thenReturn(tagDTO);

        TagDTO tagDTOExpected = tagService.findByName(name);

        assertEquals(tagDTO, tagDTOExpected);
    }

    @Test
    void should_Not_FindByName_If_Not_Exists_And_Throw() {
        when(tagJDBCTemplate.findByName(name)).thenReturn(Optional.empty());
        assertThrows(TagNotFoundException.class, () -> tagService.findByName(name));
    }

    @Test
    void should_Not_FindByName_And_Throw_IllegalArgumentException() {
        assertThrows(NullPointerException.class, () -> tagService.findByName(null));
        assertThrows(IllegalArgumentException.class, () -> tagService.findByName(""));
        assertThrows(IllegalArgumentException.class, () -> tagService.findByName(" "));
    }

    /*@Test
    void should_FindAll() {
        Optional<List<Tag>> tags = Optional.of(List.of(new Tag(1L, "tag1"),
                new Tag(2L, "tag2"),
                new Tag(3L, "tag3")));
        List<TagDTO> expected = List.of(new TagDTO(1L, "tag1"),
                new TagDTO(2L, "tag2"),
                new TagDTO(3L, "tag3"));
        when(tagJDBCTemplate.findAll()).thenReturn(tags);

        List<TagDTO> actual = tagService.findAll();

        assertEquals(actual, expected);
    }*/

    @Test
    void should_DeleteById() {
        tagService.deleteById(id);
        verify(tagJDBCTemplate, times(1)).deleteById(id);
    }

    @Test
    void should_Not_DeleteById_And_Throw() {
        assertThrows(IllegalArgumentException.class, () -> tagService.deleteById(0L));
        assertThrows(IllegalArgumentException.class, () -> tagService.deleteById(null));
    }
}