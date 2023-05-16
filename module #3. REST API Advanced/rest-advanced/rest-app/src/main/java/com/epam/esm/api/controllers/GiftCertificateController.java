package com.epam.esm.api.controllers;

import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.model.Pageable;
import com.epam.esm.core.model.QueryParams;
import com.epam.esm.service.TagService;
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
    private final TagService tagService;

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
    List<GiftCertificateDTO> findByName(@RequestParam String name,
                                        @RequestParam Integer page,
                                        @RequestParam Integer pageSize) {
        return giftCertificateService.findAllByName(name, new Pageable(page, pageSize));
    }

    @GetMapping("/find-all")
    List<GiftCertificateDTO> findAll(@RequestParam Integer page,
                                     @RequestParam Integer pageSize) {
        return giftCertificateService.findAll(new Pageable(page, pageSize));
    }

    @PostMapping("/find-by-tags")
    List<GiftCertificateDTO> findByTags(@RequestBody Set<String> tags,
                                        @RequestParam Integer page,
                                        @RequestParam Integer pageSize) {
        log.debug("FIND_BY_TAGS [{}]", tags);
        return giftCertificateService.findAllByTags(tags, new Pageable(page, pageSize));
    }

    @GetMapping("/find-all-with-params")
    List<GiftCertificateDTO> findAllWithParams(@RequestParam(required = false) String tagName,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String description,
                                               @RequestParam(required = false) String sortByName,
                                               @RequestParam(required = false) String sortByDate,
                                               @RequestParam Integer page,
                                               @RequestParam Integer pageSize) {

        QueryParams queryParams = QueryParams.builder()
                .tagName(tagName)
                .name(name)
                .description(description)
                .sortByName(sortByName)
                .sortByDate(sortByDate)
                .build();
        return giftCertificateService.findAllWithParams(queryParams, new Pageable(page, pageSize));
    }
    @GetMapping("/find/{certificateID}/tags")
    List<TagDTO> findTagsByCertificate(@PathVariable Long certificateID) {
        return tagService.findAllByCertificate(certificateID);
    }


    @PatchMapping("/update")
    GiftCertificateDTO update(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.update(giftCertificateDTO);
    }

    @DeleteMapping("/delete/{id}")
    GiftCertificateDTO deleteById(@PathVariable Long id) {
        return giftCertificateService.deleteById(id);
    }

}
