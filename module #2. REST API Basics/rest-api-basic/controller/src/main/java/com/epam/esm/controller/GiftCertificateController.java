package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.repository.util.QueryParams;
import com.epam.esm.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        giftCertificateService.save(giftCertificateDTO);
    }

    @GetMapping("/find/{id}")
    GiftCertificateDTO findById(@PathVariable("id") Long id) {
        return giftCertificateService.findById(id);
    }

    @GetMapping("/find")
    List<GiftCertificateDTO> findByName(@RequestParam("name") String name) {
        return giftCertificateService.findAllByName(name);
    }

    @GetMapping("/find-all")
    List<GiftCertificateDTO> findAll() {
        return giftCertificateService.findAll();
    }

    @GetMapping("/find-all-custom")
    List<GiftCertificateDTO> findAllCustom(@RequestParam(value = "tagName", required = false) String tagName,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "description", required = false) String description,
                                           @RequestParam(value = "sortByName", required = false) String sortByName,
                                           @RequestParam(value = "sortByDate", required = false) String sortByDate) {

        QueryParams queryParams = QueryParams.builder()
                .tagName(tagName)
                .name(name)
                .description(description)
                .sortByName(sortByName)
                .sortByDate(sortByDate)
                .build();
        if (tagName != null && !tagName.isEmpty()) {
            if (name != null && !name.isEmpty()) {
                if (sortByName != null && !sortByName.isEmpty()) {
                    return getCertificateDTOSByNameSortedByName(tagName, name, sortByName);
                } else if (sortByDate != null && !sortByDate.isEmpty()) {
                    return getCertificateDTOSByNameSortedByDate(tagName, name, sortByDate);
                }
                return giftCertificateService.findAllByTagAndName(tagName, name);
            } else if (description != null && !description.isEmpty()) {
                if (sortByName != null && !sortByName.isEmpty()) {
                    return getCertificateDTOSByDescriptionSortedByName(tagName, name, sortByName);
                } else if (sortByDate != null && !sortByDate.isEmpty()) {
                    return getCertificateDTOSByDescriptionSortedByDate(tagName, name, sortByDate);
                } else {
                    return giftCertificateService.findAllByTag(tagName);
                }
            } else {
                return giftCertificateService.findAll();
            }

        } else {
            return giftCertificateService.findAll();
        }

    }

    private List<GiftCertificateDTO> getCertificateDTOSByNameSortedByDate(String tagName, String name,
                                                                          String sortByDate) {
        if (sortByDate.equalsIgnoreCase("desc")) {
            return giftCertificateService.findAllByTagAndName(tagName, name).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getCreateDate).reversed())
                    .collect(Collectors.toList());
        } else {
            return giftCertificateService.findAllByTagAndName(tagName, name).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getCreateDate))
                    .collect(Collectors.toList());
        }
    }

    private List<GiftCertificateDTO> getCertificateDTOSByNameSortedByName(String tagName, String name,
                                                                          String sortByName) {
        if (sortByName.equalsIgnoreCase("desc")) {
            return giftCertificateService.findAllByTagAndName(tagName, name).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getName).reversed())
                    .collect(Collectors.toList());
        } else {
            return giftCertificateService.findAllByTagAndName(tagName, name).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getName))
                    .collect(Collectors.toList());
        }
    }

    private List<GiftCertificateDTO> getCertificateDTOSByDescriptionSortedByDate(String tagName, String description,
                                                                                 String sortByDate) {
        if (sortByDate.equalsIgnoreCase("desc")) {
            return giftCertificateService.findAllByTagAndDescription(tagName, description).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getCreateDate).reversed())
                    .collect(Collectors.toList());
        } else {
            return giftCertificateService.findAllByTagAndDescription(tagName, description).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getCreateDate))
                    .collect(Collectors.toList());
        }
    }

    private List<GiftCertificateDTO> getCertificateDTOSByDescriptionSortedByName(String tagName, String description,
                                                                                 String sortByName) {
        if (sortByName.equalsIgnoreCase("desc")) {
            return giftCertificateService.findAllByTagAndDescription(tagName, description).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getName).reversed())
                    .collect(Collectors.toList());
        } else {
            return giftCertificateService.findAllByTagAndDescription(tagName, description).stream()
                    .sorted(Comparator.comparing(GiftCertificateDTO::getName))
                    .collect(Collectors.toList());
        }
    }

    @PutMapping("/update/{id}")
    void update(@PathVariable() Long id, @RequestBody GiftCertificateDTO giftCertificateDTO) {
        giftCertificateService.update(id, giftCertificateDTO);
    }

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable("id") Long id) {
        giftCertificateService.deleteById(id);
    }

}
