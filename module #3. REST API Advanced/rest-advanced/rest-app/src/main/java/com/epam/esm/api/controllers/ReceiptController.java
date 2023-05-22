package com.epam.esm.api.controllers;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.pagination.Pageable;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ReceiptService;
import com.epam.esm.service.UserService;
import com.epam.esm.core.model.request.CreateReceiptRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/receipts")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final GiftCertificateService giftCertificateService;
    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ReceiptDTO save(@RequestBody CreateReceiptRequestBody receiptRequestBody) {
        return receiptService.save(receiptRequestBody);
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

    @GetMapping("/find/{receiptID}/gift-certificates")
    List<GiftCertificateDTO> findGiftCertificatesByReceipt(@PathVariable Long receiptID,
                                                           @RequestParam Integer page,
                                                           @RequestParam Integer pageSize) {
        return giftCertificateService.findAllByReceipt(receiptID, new Pageable(page, pageSize));
    }

    @GetMapping("/find/{receiptID}/user")
    UserDTO findUserByReceipt(@PathVariable Long receiptID) {
        return userService.findByReceipt(receiptID);
    }

    @DeleteMapping("/delete/{id}")
    ReceiptDTO deleteById(@PathVariable Long id) {
        return receiptService.deleteById(id);
    }
}
