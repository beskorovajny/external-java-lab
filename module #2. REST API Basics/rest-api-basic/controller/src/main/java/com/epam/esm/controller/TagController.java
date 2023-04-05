package com.epam.esm.controller;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody TagDTO tagDTO) {
        tagService.save(tagDTO);
    }

    @GetMapping("/find/{id}")
    TagDTO findById(@PathVariable("id") Long id) {
        return tagService.findById(id);
    }

    @GetMapping("/find")
    TagDTO findByName(@RequestParam("name") String name) {
        return tagService.findByName(name);
    }

    @GetMapping("/find-all")
    List<TagDTO> findAll() {
        return tagService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable("id") Long id) {
        tagService.deleteById(id);
    }
}
