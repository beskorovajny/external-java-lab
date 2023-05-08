package com.epam.esm.api.controllers;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.repository.utils.QueryParams;
import com.epam.esm.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
@Slf4j
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    GiftCertificateDTO save(@RequestBody GiftCertificateDTO giftCertificateDTO) {
       return giftCertificateService.save(giftCertificateDTO);
    }

    @GetMapping("/find/{id}")
    GiftCertificateDTO findById(@PathVariable Long id) {
        return giftCertificateService.findById(id);
    }

    @GetMapping("/find")
    List<GiftCertificateDTO> findByName(@RequestParam String name) {
        return giftCertificateService.findAllByName(name);
    }

    @GetMapping("/find-all")
    List<GiftCertificateDTO> findAll() {
        return giftCertificateService.findAll();
    }

    @PostMapping("/find-by-tags")
    List<GiftCertificateDTO> findByTags(@RequestBody Set<String> tags) {
        log.debug("FIND_BY_TAGS [{}]", tags);
        return giftCertificateService.findAllByTags(tags);
    }

    @GetMapping("/find-all-with-params")
    List<GiftCertificateDTO> findAllWithParams(@RequestParam(required = false) String tagName,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String description,
                                               @RequestParam(required = false) String sortByName,
                                               @RequestParam(required = false) String sortByDate) {

        QueryParams queryParams = QueryParams.builder()
                .tagName(tagName)
                .name(name)
                .description(description)
                .sortByName(sortByName)
                .sortByDate(sortByDate)
                .build();
        return giftCertificateService.findAllWithParams(queryParams);
    }


    @PatchMapping("/update")
   GiftCertificateDTO update(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.update(giftCertificateDTO);
    }

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id) {
        giftCertificateService.deleteById(id);
    }

}
