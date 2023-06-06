package com.epam.esm.api.controllers;

import com.epam.esm.api.assembler.TagModelAssembler;
import com.epam.esm.api.model.TagModel;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagModelAssembler tagModelAssembler;
    private final PagedResourcesAssembler<TagDTO> pagedResourcesAssembler;

    @PostMapping("/create")
    public ResponseEntity<TagModel> save(@RequestBody TagDTO tag) {
        TagDTO tagDTO = tagService.save(tag);
        TagModel tagModel = tagModelAssembler.toModel(tagDTO);
        return new ResponseEntity<>(tagModel, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TagModel> findByID(@PathVariable Long id) {
        TagDTO tagDTO = tagService.findById(id);
        TagModel tagModel = tagModelAssembler.toModel(tagDTO);
        return new ResponseEntity<>(tagModel, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<TagModel> findByName(@RequestParam String name) {
        TagDTO tagDTO = tagService.findByName(name);
        TagModel tagModel = tagModelAssembler.toModel(tagDTO);
        return new ResponseEntity<>(tagModel, HttpStatus.OK);
    }

    @GetMapping("/find-most-widely-used-tag")
    public ResponseEntity<TagModel> findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts() {
        TagDTO tagDTO = tagService.findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();
        TagModel tagModel = tagModelAssembler.toModel(tagDTO);
        return new ResponseEntity<>(tagModel, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<PagedModel<TagModel>> findAll(Pageable pageable) {
        Page<TagDTO> pageTags = tagService.findAll(pageable);
        PagedModel<TagModel> pagedModel = pagedResourcesAssembler.toModel(pageTags, tagModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public TagDTO deleteByID(@PathVariable Long id) {
        return tagService.deleteByID(id);
    }
}

