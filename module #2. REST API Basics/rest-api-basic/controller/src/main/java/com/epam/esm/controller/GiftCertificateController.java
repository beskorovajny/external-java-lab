package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        if (tagName != null && !tagName.isEmpty()) {
            if (name != null && !name.isEmpty()) {
                return giftCertificateService.findAllByTagAndName(tagName, name);
            } else if (description != null && !description.isEmpty()) {
                return giftCertificateService.findAllByTagAndDescription(tagName, description);
            } else {
                return giftCertificateService.findAllByTag(tagName);
            }
        } else {
            if (name != null && !name.isEmpty()) {
                return giftCertificateService.findAllByName(name);
            } else if (description != null && !description.isEmpty()) {
                return giftCertificateService.findAllByDescription(description);
            } else {
                return giftCertificateService.findAll();
            }
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
