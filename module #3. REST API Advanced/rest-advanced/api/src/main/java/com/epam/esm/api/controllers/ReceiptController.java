package com.epam.esm.api.controllers;

import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.repository.utils.Pageable;
import com.epam.esm.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/receipts")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody ReceiptDTO receiptDTO) {
        receiptService.save(receiptDTO);
    }

    @GetMapping("/find/{id}")
    ReceiptDTO findById(@PathVariable Long id) {
        return receiptService.findById(id);
    }

    @GetMapping("/find-all")
    List<ReceiptDTO> findAll(@RequestParam Integer page,
                             @RequestParam Integer pageSize) {
        return receiptService.findAll(new Pageable(page, pageSize));
    }

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id) {
        receiptService.deleteById(id);
    }
}
