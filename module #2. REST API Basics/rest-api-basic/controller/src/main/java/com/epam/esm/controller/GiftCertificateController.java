package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.service.GiftCertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    GiftCertificateDTO findByName(@RequestParam("name") String name) {
        return giftCertificateService.findByName(name);
    }

    @GetMapping("/find-all")
    List<GiftCertificateDTO> findAll() {
        return giftCertificateService.findAll();
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
