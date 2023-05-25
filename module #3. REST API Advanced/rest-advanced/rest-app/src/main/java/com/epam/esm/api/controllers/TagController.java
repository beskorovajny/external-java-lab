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
    @ResponseStatus(HttpStatus.CREATED)
    public TagDTO save(@RequestBody TagDTO tagDTO) {
        return tagService.save(tagDTO);
    }

    @GetMapping("/find/{id}")
    public TagDTO findByID(@PathVariable Long id) {
        return tagService.findById(id);
    }

    @GetMapping("/find")
    public TagDTO findByName(@RequestParam String name) {
        return tagService.findByName(name);
    }

    @GetMapping("/find-most-widely-used-tag")
    public TagDTO findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts() {
        return tagService.findMostWidelyUsedTagOfUserWithHighestCostOfAllReceipts();
    }

    @GetMapping("/find-all")
    public ResponseEntity<PagedModel<TagModel>> findAll(Pageable pageable) {
        Page<TagDTO> pageTags = tagService.findAll(pageable);
        PagedModel<TagModel> pagedModel = pagedResourcesAssembler.toModel(pageTags, tagModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public TagDTO deleteByID(@PathVariable Long id) {
        return tagService.deleteById(id);
    }
}

