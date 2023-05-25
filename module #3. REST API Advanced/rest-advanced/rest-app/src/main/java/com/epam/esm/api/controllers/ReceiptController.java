package com.epam.esm.api.controllers;

import com.epam.esm.api.assembler.GiftCertificateModelAssembler;
import com.epam.esm.api.assembler.ReceiptModelAssembler;
import com.epam.esm.api.model.GiftCertificateModel;
import com.epam.esm.api.model.ReceiptModel;
import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.UserDTO;
import com.epam.esm.core.model.request.CreateReceiptRequestBody;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ReceiptService;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/receipts")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final GiftCertificateService giftCertificateService;
    private final UserService userService;
    private final ReceiptModelAssembler receiptModelAssembler;
    private final GiftCertificateModelAssembler giftCertificateModelAssembler;
    private final PagedResourcesAssembler<ReceiptDTO> receiptPagedResourcesAssembler;
    private final PagedResourcesAssembler<GiftCertificateDTO> certificatePagedResourcesAssembler;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiptDTO save(@RequestBody CreateReceiptRequestBody receiptRequestBody) {
        return receiptService.save(receiptRequestBody);
    }

    @GetMapping("/find/{id}")
    public ReceiptDTO findByID(@PathVariable Long id) {
        return receiptService.findById(id);
    }

    @GetMapping("/find-all")
    public ResponseEntity<PagedModel<ReceiptModel>> findAll(Pageable pageable) {
        Page<ReceiptDTO> receiptPage = receiptService.findAll(pageable);
        PagedModel<ReceiptModel> pagedModel = receiptPagedResourcesAssembler.toModel(receiptPage, receiptModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping("/find/{receiptID}/gift-certificates")
    public ResponseEntity<PagedModel<GiftCertificateModel>> findGiftCertificatesByReceipt(@PathVariable Long receiptID,
                                                                                          Pageable pageable) {
        Page<GiftCertificateDTO> certificatePage = giftCertificateService.findAllByReceipt(receiptID, pageable);
        PagedModel<GiftCertificateModel> pagedModel = certificatePagedResourcesAssembler
                .toModel(certificatePage, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping("/find/{receiptID}/user")
    public UserDTO findUserByReceipt(@PathVariable Long receiptID) {
        return userService.findByReceipt(receiptID);
    }

    @DeleteMapping("/delete/{id}")
    public ReceiptDTO deleteByID(@PathVariable Long id) {
        return receiptService.deleteById(id);
    }
}
