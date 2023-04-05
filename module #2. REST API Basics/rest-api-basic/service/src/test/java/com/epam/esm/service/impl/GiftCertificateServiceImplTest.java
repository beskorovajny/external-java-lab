package com.epam.esm.service.impl;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.exception.model.GiftCertificateAlreadyExistsException;
import com.epam.esm.exception.model.GiftCertificateNotFoundException;
import com.epam.esm.exception.model.TagAlreadyExistsException;
import com.epam.esm.exception.model.TagNotFoundException;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.impl.jdbctemplate.mysql.GiftCertificateJDBCTemplate;
import com.epam.esm.service.mapping.impl.GiftCertificateMappingServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    @Mock
    GiftCertificateJDBCTemplate certificateJDBCTemplate;
    @Mock
    GiftCertificateMappingServiceImpl mappingService;
    @InjectMocks
    GiftCertificateServiceImpl certificateService;
    GiftCertificateDTO certificateDTO;
    GiftCertificate expectedCertificate;
    long id;
    String name;


    @BeforeEach
    void setUp() {
        id = 1L;
        name = "testCertificate";
        certificateDTO = GiftCertificateDTO.builder()
                .id(id)
                .name(name)
                .description("desc")
                .price(5.0)
                .duration(2)
                .build();
        expectedCertificate = GiftCertificate.builder()
                .id(id)
                .name(name)
                .description("desc")
                .price(5.0)
                .duration(2)
                .createDate(LocalDateTime.of(2023,3,5,13,11,55))
                .build();
    }

    @AfterEach
    void tearDown() {
        expectedCertificate = null;
        certificateDTO = null;
    }

    @Test
    void should_Save() {
        when(certificateJDBCTemplate.findByName(certificateDTO.getName())).thenReturn(Optional.empty());
        when(mappingService.mapFromDto(certificateDTO)).thenReturn(expectedCertificate);
        when(certificateJDBCTemplate.save(expectedCertificate)).thenReturn(id);

        certificateService.save(certificateDTO);

        verify(certificateJDBCTemplate, times(1)).save(expectedCertificate);
    }

    @Test
    void should_Not_Save_If_Exists() {
        when(certificateJDBCTemplate.findByName(certificateDTO.getName())).thenReturn(Optional.of(expectedCertificate));
        assertThrows(GiftCertificateAlreadyExistsException.class, () -> certificateService.save(certificateDTO));
    }

    @Test
    void should_FindById() {
        when(certificateJDBCTemplate.findById(id)).thenReturn(Optional.of(expectedCertificate));
        when(mappingService.mapToDto(expectedCertificate)).thenReturn(certificateDTO);

        GiftCertificateDTO actualDTO = certificateService.findById(id);

        assertEquals(certificateDTO, actualDTO);
    }

    @Test
    void should_Not_FindById_If_Not_Exists_And_Throw() {
        when(certificateJDBCTemplate.findById(id)).thenReturn(Optional.empty());
        assertThrows(GiftCertificateNotFoundException.class, () -> certificateService.findById(id));
    }

    @Test
    void should_Not_FindById_And_Throw_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> certificateService.findById(0L));
        assertThrows(IllegalArgumentException.class, () -> certificateService.findById(null));
    }


    @Test
    void should_FindByName() {
        when(certificateJDBCTemplate.findByName(name)).thenReturn(Optional.of(expectedCertificate));
        when(mappingService.mapToDto(expectedCertificate)).thenReturn(certificateDTO);

        GiftCertificateDTO actualDTO = certificateService.findByName(name);

        assertEquals(certificateDTO, actualDTO);
    }

    @Test
    void should_Not_FindByName_If_Not_Exists_And_Throw() {
        when(certificateJDBCTemplate.findByName(name)).thenReturn(Optional.empty());
        assertThrows(GiftCertificateNotFoundException.class, () -> certificateService.findByName(name));
    }

    @Test
    void should_Not_FindByName_And_Throw_IllegalArgumentException() {
        assertThrows(NullPointerException.class, () -> certificateService.findByName(null));
        assertThrows(IllegalArgumentException.class, () -> certificateService.findByName(""));
        assertThrows(IllegalArgumentException.class, () -> certificateService.findByName(" "));
    }

    @Test
    void should_FindAll() {
        List<GiftCertificateDTO> certificateDTOS = certificateService.findAll();
        verify(certificateJDBCTemplate, times(1)).findAll();
        assertNotNull(certificateDTOS);
    }

    @Test
    void should_Update() {
        when(mappingService.mapFromDto(certificateDTO)).thenReturn(expectedCertificate);
        certificateService.update(id, certificateDTO);
        verify(certificateJDBCTemplate, times(1)).update(id, expectedCertificate);
    }

    @Test
    void should_Not_Update_And_Throw() {
        assertThrows(IllegalArgumentException.class, () -> certificateService.update(0L, certificateDTO));
        assertThrows(NullPointerException.class, () -> certificateService.update(1L, null));
        assertThrows(IllegalArgumentException.class, () -> certificateService.update(null, certificateDTO));
    }

    @Test
    void should_DeleteById() {
        certificateService.deleteById(id);
        verify(certificateJDBCTemplate, times(1)).deleteById(id);
    }

    @Test
    void should_Not_DeleteById_And_Throw() {
        assertThrows(IllegalArgumentException.class, () -> certificateService.deleteById(0L));
        assertThrows(IllegalArgumentException.class, () -> certificateService.deleteById(null));
    }
}