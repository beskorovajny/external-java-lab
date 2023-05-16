package com.epam.esm.api.controllers;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.Pageable;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ReceiptService;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/receipts")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final GiftCertificateService giftCertificateService;
    private final UserService userService;

    @PostMapping("/create/{userID}")
    @ResponseStatus(HttpStatus.CREATED)
    ReceiptDTO save(@PathVariable Long userID, @RequestBody Set<Long> giftCertificatesID) {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userID);
        receiptDTO.setUserDTO(userDTO);
        if (!giftCertificatesID.isEmpty()) {
            giftCertificatesID.forEach(id -> {
                GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
                giftCertificateDTO.setId(id);
                receiptDTO.getGiftCertificates().add(giftCertificateDTO);
            });
        }
        return receiptService.save(receiptDTO);
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
    List<GiftCertificateDTO> findGiftCertificatesByReceipt(@PathVariable Long receiptID) {
        return giftCertificateService.findAllByReceipt(receiptID);
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
