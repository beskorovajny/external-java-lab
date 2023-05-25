package com.epam.esm.api.controllers;

import com.epam.esm.api.assembler.GiftCertificateModelAssembler;
import com.epam.esm.api.assembler.TagModelAssembler;
import com.epam.esm.api.model.GiftCertificateModel;
import com.epam.esm.api.model.TagModel;
import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.model.query.QueryParams;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
@Slf4j
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;
    private final TagService tagService;
    private final GiftCertificateModelAssembler giftCertificateModelAssembler;
    private final TagModelAssembler tagModelAssembler;
    private final PagedResourcesAssembler<GiftCertificateDTO> certificatePagedResourcesAssembler;
    private final PagedResourcesAssembler<TagDTO> tagPagedResourcesAssembler;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDTO save(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.save(giftCertificateDTO);
    }

    @GetMapping("/find/{id}")
    public GiftCertificateDTO findByID(@PathVariable Long id) {
        return giftCertificateService.findById(id);
    }

    @GetMapping("/find")
    public ResponseEntity<PagedModel<GiftCertificateModel>> findByName(@RequestParam String name,
                                                                       Pageable pageable) {
        Page<GiftCertificateDTO> certificatePage = giftCertificateService.findAllByName(name, pageable);
        PagedModel<GiftCertificateModel> pagedModel = certificatePagedResourcesAssembler
                .toModel(certificatePage, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<PagedModel<GiftCertificateModel>> findAll(Pageable pageable) {
        Page<GiftCertificateDTO> certificatePage = giftCertificateService.findAll(pageable);
        PagedModel<GiftCertificateModel> pagedModel = certificatePagedResourcesAssembler
                .toModel(certificatePage, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping("/find-by-tags")
    public ResponseEntity<PagedModel<GiftCertificateModel>> findByTags(@RequestBody Set<String> tags,
                                                                       Pageable pageable) {
        log.debug("FIND_BY_TAGS [{}]", tags);
        Page<GiftCertificateDTO> certificatePage = giftCertificateService.findAllByTags(tags, pageable);
        PagedModel<GiftCertificateModel> pagedModel = certificatePagedResourcesAssembler
                .toModel(certificatePage, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping("/find-all-with-params")
    public ResponseEntity<PagedModel<GiftCertificateModel>> findAllWithParams(
            @RequestParam(required = false) String tagName,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String sortByName,
            @RequestParam(required = false) String sortByDate,
            Pageable pageable) {

        QueryParams queryParams = QueryParams.builder()
                .tagName(tagName)
                .name(name)
                .description(description)
                .sortByName(sortByName)
                .sortByDate(sortByDate)
                .build();
        Page<GiftCertificateDTO> certificatePage = giftCertificateService.findAllWithParams(queryParams, pageable);
        PagedModel<GiftCertificateModel> pagedModel = certificatePagedResourcesAssembler
                .toModel(certificatePage, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping("/find/{certificateID}/tags")
    public ResponseEntity<PagedModel<TagModel>> findTagsByCertificate(@PathVariable Long certificateID,
                                                                     Pageable pageable) {
        Page<TagDTO> tagPage = tagService.findAllByCertificate(certificateID, pageable);
        PagedModel<TagModel> pagedModel = tagPagedResourcesAssembler.toModel(tagPage, tagModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }


    @PatchMapping("/update")
    public GiftCertificateDTO update(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.update(giftCertificateDTO);
    }

    @PatchMapping("/update-price")
    public GiftCertificateDTO updatePrice(@RequestParam Long giftCertificateID,
                                          @RequestParam Double price) {
        return giftCertificateService.updatePrice(giftCertificateID, price);
    }

    @DeleteMapping("/delete/{id}")
    public GiftCertificateDTO deleteByID(@PathVariable Long id) {
        return giftCertificateService.deleteById(id);
    }

}
